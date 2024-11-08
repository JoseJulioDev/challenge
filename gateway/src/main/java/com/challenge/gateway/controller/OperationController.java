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

    @PostMapping("/execute")
    public ResponseEntity<BigDecimal> executeOperation(@RequestBody OperationDTO operationDTO) {
        return ResponseEntity.ok(operationService.executeOperation(operationDTO));
    }

    @PostMapping("/random-string")
    public ResponseEntity<String> randomString(@RequestParam Long userId) {
        String randomString = operationService.randomString(userId);
        return ResponseEntity.ok(randomString);
    }

}
