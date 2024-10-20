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

    public BigDecimal executeOperation(Long userId, String operationType, BigDecimal a, BigDecimal b) {
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

        BigDecimal resultado = performOperation(operationType, a, b);

        recordService.save(operation, user, resultado, balanceNew, "Result: " + resultado);

        return resultado;
    }

    private BigDecimal performOperation(String operationType, BigDecimal a, BigDecimal b) {
        BigDecimal result;
        switch (operationType) {
            case "add":
                result = a.add(b);
                break;
            case "subtract":
                result = a.subtract(b);
                break;
            case "multiply":
                result = a.multiply(b);
                break;
            case "divide":
                if (b.compareTo(BigDecimal.ZERO) == 0) {
                    throw new IllegalArgumentException("Division by zero not allowed.");
                }
                result = a.divide(b, 10, RoundingMode.HALF_UP);
                break;
            case "sqrt":
                if (a.compareTo(BigDecimal.ZERO) < 0) {
                    throw new IllegalArgumentException("Square root of negative number not allowed.");
                }
                result = new BigDecimal(Math.sqrt(a.doubleValue()));
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


