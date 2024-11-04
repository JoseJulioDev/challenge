package com.challenge.gateway.service;

import com.challenge.gateway.dto.OperationDTO;
import com.challenge.gateway.dto.OperationResponseDTO;
import com.challenge.gateway.infrastructure.clients.OperationClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;

@Service
public class OperationService {

    private final OperationClient operationClient;

    public OperationService(OperationClient operationClient) {
        this.operationClient = operationClient;
    }

    public BigDecimal add(Long userId, BigDecimal a, BigDecimal b) {
        return operationClient.add(userId, a, b);
    }

    public BigDecimal subtract(Long userId, BigDecimal a, BigDecimal b) {
        return operationClient.subtract(userId, a, b);
    }

    public BigDecimal multiply(Long userId, BigDecimal a, BigDecimal b) {
        return operationClient.multiply(userId, a, b);
    }

    public OperationResponseDTO divide(Long userId, BigDecimal a, BigDecimal b) {
        return operationClient.divide(userId, a, b);
    }

    public OperationResponseDTO squareRoot(Long userId, BigDecimal a) {
        return operationClient.squareRoot(userId, a);
    }

    public String randomString(Long userId) {
        return operationClient.randomString(userId);
    }


    public BigDecimal executeOperation(OperationDTO operationDTO) {
        return operationClient.executeOperation(operationDTO);
    }
}
