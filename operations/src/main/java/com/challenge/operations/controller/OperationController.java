package com.challenge.operations.controller;

import com.challenge.operations.dto.OperationDTO;
import com.challenge.operations.entity.Operation;
import com.challenge.operations.exception.OperationNotFoundException;
import com.challenge.operations.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/operations")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @PostMapping("/execute")
    public ResponseEntity<BigDecimal> executeOperation(@RequestBody OperationDTO operationDTO) {
        return ResponseEntity.ok(operationService.executeOperation(operationDTO));
    }

    @PostMapping("/random-string")
    public ResponseEntity<String> randomString(@RequestParam Long userId) {
        String randomString = operationService.generateRandomString(userId);
        return ResponseEntity.ok(randomString);
    }

    @GetMapping
    public ResponseEntity<List<Operation>> getAllOperations() {
        List<Operation> operations = operationService.listAllOperations();
        return ResponseEntity.ok(operations);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOperation(@PathVariable Long id) {
        try {
            operationService.deleteOperation(id);
            return ResponseEntity.ok("Operation deleted successfully.");
        } catch (OperationNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }
}
