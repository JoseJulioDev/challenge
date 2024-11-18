package com.challenge.gateway.infrastructure.clients;

import com.challenge.gateway.config.FeignConfig;
import com.challenge.gateway.dto.Operation;
import com.challenge.gateway.dto.OperationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(value="operationsClient", url = "localhost:8082/api/operations",  configuration = FeignConfig.class)
public interface OperationClient {

    @PostMapping("/random-string")
     String randomString(@RequestParam Long userId);

    @PostMapping("/execute")
    BigDecimal executeOperation(@RequestBody OperationDTO operationDTO);

    @GetMapping
    public List<Operation> getAllOperations();

    @DeleteMapping("/{id}")
    public String deleteOperation(@PathVariable Long id);
}
