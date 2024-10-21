package com.challenge.operations.service;

import com.challenge.operations.entity.Operation;
import com.challenge.operations.entity.User;
import com.challenge.operations.generator.RandomStringGenerator;
import com.challenge.operations.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;

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

    public BigDecimal executeOperation(Long userId, String operationType, BigDecimal value1, BigDecimal value2) {
        User user = userService.findById(userId);

        Operation operation = operationRepository.findByType(operationType)
                .orElseThrow(() -> new IllegalArgumentException("Operation not found."));

        BigDecimal currentBalance = user.getBalance();
        BigDecimal costOperation = operation.getCost();

        if (currentBalance.compareTo(costOperation) < 0) {
            throw new IllegalArgumentException("Insufficient balance to carry out the operation.");
        }

        BigDecimal balanceNew = currentBalance.subtract(costOperation);
        user.setBalance(balanceNew);
        userService.updateUser(user);

        BigDecimal resultado = performOperation(operationType, value1, value2);

        recordService.save(operation, user, resultado, balanceNew, "Result: " + resultado);

        return resultado;
    }

    private BigDecimal performOperation(String operationType, BigDecimal value1, BigDecimal value2) {
        BigDecimal result;
        switch (operationType) {
            case "add":
                result = value1.add(value2);
                break;
            case "subtract":
                result = value1.subtract(value2);
                break;
            case "multiply":
                result = value1.multiply(value2);
                break;
            case "divide":
                if (value2.compareTo(BigDecimal.ZERO) == 0) {
                    throw new IllegalArgumentException("Division by zero not allowed.");
                }
                result = value1.divide(value2, 10, RoundingMode.HALF_UP);
                break;
            case "sqrt":
                if (value1.compareTo(BigDecimal.ZERO) < 0) {
                    throw new IllegalArgumentException("Square root of negative number not allowed.");
                }
                result = new BigDecimal(Math.sqrt(value1.doubleValue()));
                break;
            default:
                throw new IllegalArgumentException("Invalid operation type.");
        }

        return result;
    }

    public String generateRandomString(Long userId) {

        User user = userService.findById(userId);

        Operation operation = operationRepository.findByType("random-string")
                .orElseThrow(() -> new IllegalArgumentException("Operation not found."));

        BigDecimal currentBalance = user.getBalance();
        BigDecimal costOperation = operation.getCost();

        if (currentBalance.compareTo(costOperation) < 0) {
            throw new IllegalArgumentException("Insufficient balance to carry out the operation.");
        }

        BigDecimal balanceNew = currentBalance.subtract(costOperation);
        user.setBalance(balanceNew);
        userService.updateUser(user);

        String randomString = RandomStringGenerator.generateRandomString(stringLength);

        recordService.save(operation, user, BigDecimal.ZERO, balanceNew, "Generated random string: " + randomString);

        return randomString;
    }
}


