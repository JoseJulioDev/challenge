package com.challenge.operations.service;

import com.challenge.operations.dto.OperationDTO;
import com.challenge.operations.entity.Operation;
import com.challenge.operations.entity.User;
import com.challenge.operations.exception.InsufficientBalanceException;
import com.challenge.operations.exception.OperationNotFoundException;
import com.challenge.operations.generator.RandomStringGenerator;
import com.challenge.operations.repository.OperationRepository;
import com.challenge.operations.util.ExpressionEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OperationService {

    @Autowired
    private UserService userService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private OperationRepository operationRepository;

    @Value("${randomsize}")
    private int stringLength;

    public BigDecimal executeOperation(OperationDTO operationDTO) {
        User user = userService.findById(operationDTO.getUserId());

        // Evaluate the full expression and get the result
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        Double result = evaluator.evaluate(operationDTO.getExpression());
        BigDecimal finalResult = BigDecimal.valueOf(result);

        // Get all operations present in the expression
        List<String> operations = detectAllOperationTypes(operationDTO.getExpression());

        BigDecimal currentBalance = user.getBalance();

        for (String operationType : operations) {
            // Recover the cost of the operation
            Operation operation = operationRepository.findByType(operationType)
                    .orElseThrow(() -> new OperationNotFoundException("Operation not found: " + operationType));

            BigDecimal costOperation = operation.getCost();

            // Check if there is enough balance for each operation
            if (currentBalance.compareTo(costOperation) < 0) {
                throw new InsufficientBalanceException("Insufficient balance to carry out the operation: " + operationType);
            }

            // Deduct the cost of the transaction from the balance
            currentBalance = currentBalance.subtract(costOperation);
        }

        // Update user balance after all operations
        user.setBalance(currentBalance);
        userService.updateUser(user);

        for (String operationType : operations) {
            Operation operation = operationRepository.findByType(operationType)
                    .orElseThrow(() -> new OperationNotFoundException("Operation not found: " + operationType));

            // Save a record for each operation
            recordService.save(
                    operation,
                    user,
                    BigDecimal.ZERO,
                    currentBalance,
                    "Operation: " + operationType,
                    operationDTO.getExpression()
            );
        }

        return finalResult;
    }

    private List<String> detectAllOperationTypes(String expression) {
        List<String> operations = new ArrayList<>();

        if (expression.contains("*")) operations.add("multiply");
        if (expression.contains("/")) operations.add("divide");
        if (expression.contains("+")) operations.add("add");
        if (expression.contains("-")) operations.add("subtract");
        if (expression.contains("sqrt")) operations.add("sqrt");

        return operations;
    }

    public String generateRandomString(Long userId) {

        User user = userService.findById(userId);

        Operation operation = operationRepository.findByType("random-string")
                .orElseThrow(() -> new IllegalArgumentException("Operation not found."));

        BigDecimal currentBalance = user.getBalance();
        BigDecimal costOperation = operation.getCost();

        if (currentBalance.compareTo(costOperation) < 0) {
            throw new InsufficientBalanceException("Insufficient balance to carry out the operation.");
        }

        BigDecimal balanceNew = currentBalance.subtract(costOperation);
        user.setBalance(balanceNew);
        userService.updateUser(user);

        String randomString = RandomStringGenerator.generateRandomString(stringLength);

        recordService.save(operation, user, BigDecimal.ZERO, balanceNew, "Generated random string: " + randomString, "random-string");

        return randomString;
    }

}
