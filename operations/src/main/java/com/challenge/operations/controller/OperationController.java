package com.challenge.operations.controller;

import com.challenge.operations.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/operations")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @PostMapping("/add")
    public ResponseEntity<BigDecimal> add(@RequestParam Long userId, @RequestParam BigDecimal value1, @RequestParam BigDecimal value2) {
        return ResponseEntity.ok(operationService.executeOperation(userId, "add", value1, value2));
    }

    @PostMapping("/subtract")
    public ResponseEntity<BigDecimal> subtract(@RequestParam Long userId, @RequestParam BigDecimal value1, @RequestParam BigDecimal value2) {
        return ResponseEntity.ok(operationService.executeOperation(userId, "subtract", value1, value2));
    }

    @PostMapping("/multiply")
    public ResponseEntity<BigDecimal> multiply(@RequestParam Long userId, @RequestParam BigDecimal value1, @RequestParam BigDecimal value2) {
        return ResponseEntity.ok(operationService.executeOperation(userId, "multiply", value1, value2));
    }

    @PostMapping("/divide")
    public ResponseEntity<?> divide(@RequestParam Long userId, @RequestParam BigDecimal value1, @RequestParam BigDecimal value2) {
        try {
            return ResponseEntity.ok(operationService.executeOperation(userId, "divide", value1, value2));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/sqrt")
    public ResponseEntity<?> squareRoot(@RequestParam Long userId, @RequestParam BigDecimal value) {
        try {
            return ResponseEntity.ok(operationService.executeOperation(userId, "sqrt", value, BigDecimal.ZERO));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/random-string")
    public ResponseEntity<String> randomString(@RequestParam Long userId) {
        String randomString = operationService.generateRandomString(userId);
        return ResponseEntity.ok(randomString);
    }
}
