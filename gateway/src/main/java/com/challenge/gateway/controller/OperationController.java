package com.challenge.gateway.controller;

import com.challenge.gateway.dto.OperationDTO;
import com.challenge.gateway.service.OperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/operations")
public class OperationController {

    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @PostMapping("/add")
    public ResponseEntity<BigDecimal> add(@RequestParam Long userId, @RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        return ResponseEntity.ok(operationService.add(userId, a, b));
    }

    @PostMapping("/subtract")
    public ResponseEntity<BigDecimal> subtract(@RequestParam Long userId, @RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        return ResponseEntity.ok(operationService.subtract(userId, a, b));
    }

    @PostMapping("/multiply")
    public ResponseEntity<BigDecimal> multiply(@RequestParam Long userId, @RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        return ResponseEntity.ok(operationService.multiply(userId,  a, b));
    }

    @PostMapping("/divide")
    public ResponseEntity<?> divide(@RequestParam Long userId, @RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        try {
            return ResponseEntity.ok(operationService.divide(userId, a, b));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/sqrt")
    public ResponseEntity<?> squareRoot(@RequestParam Long userId, @RequestParam BigDecimal a) {
        try {
            return ResponseEntity.ok(operationService.squareRoot(userId, a));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/random-string")
    public ResponseEntity<String> randomString(@RequestParam Long userId) {
        return ResponseEntity.ok(operationService.randomString(userId));
    }

    @PostMapping("/execute")
    public ResponseEntity<BigDecimal> executeOperation(@RequestBody OperationDTO operationDTO) {
        return ResponseEntity.ok(operationService.executeOperation(operationDTO));
    }

}
