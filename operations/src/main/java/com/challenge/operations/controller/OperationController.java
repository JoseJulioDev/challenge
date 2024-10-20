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
    public ResponseEntity<BigDecimal> add(@RequestParam Long userId, @RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        return ResponseEntity.ok(operationService.executeOperation(userId, "add", a, b));
    }

    @PostMapping("/subtract")
    public ResponseEntity<BigDecimal> subtract(@RequestParam Long userId, @RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        return ResponseEntity.ok(operationService.executeOperation(userId, "subtract", a, b));
    }

    @PostMapping("/multiply")
    public ResponseEntity<BigDecimal> multiply(@RequestParam Long userId, @RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        return ResponseEntity.ok(operationService.executeOperation(userId, "multiply", a, b));
    }

    @PostMapping("/divide")
    public ResponseEntity<?> divide(@RequestParam Long userId, @RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        try {
            return ResponseEntity.ok(operationService.executeOperation(userId, "divide", a, b));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/sqrt")
    public ResponseEntity<?> squareRoot(@RequestParam Long userId, @RequestParam BigDecimal a) {
        try {
            return ResponseEntity.ok(operationService.executeOperation(userId, "sqrt", a, BigDecimal.ZERO));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/random-string")
    public ResponseEntity<String> getRandomString(@RequestParam Long userId) {
        String randomString = operationService.generateRandomString(userId);
        return ResponseEntity.ok(randomString);
    }
}
