package com.challenge.gateway.infrastructure.clients;

import com.challenge.gateway.config.FeignConfig;
import com.challenge.gateway.dto.OperationDTO;
import com.challenge.gateway.dto.OperationResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value="operationsClient", url = "localhost:8082/api/operations",  configuration = FeignConfig.class)
public interface OperationClient {

    @PostMapping("/random-string")
     String randomString(@RequestParam Long userId);

    @PostMapping("/execute")
    BigDecimal executeOperation(@RequestBody OperationDTO operationDTO);
}
