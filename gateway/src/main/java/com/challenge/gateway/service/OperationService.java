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

    public String randomString(Long userId) {
        return operationClient.randomString(userId);
    }

    public BigDecimal executeOperation(OperationDTO operationDTO) {
        return operationClient.executeOperation(operationDTO);
    }
}
