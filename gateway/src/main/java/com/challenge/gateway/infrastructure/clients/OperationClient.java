package com.challenge.gateway.infrastructure.clients;

import com.challenge.gateway.dto.OperationResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value="operationsClient", url = "localhost:8082/api/operations")
public interface OperationClient {

    @PostMapping("/add")
    BigDecimal add(@RequestParam Long userId, @RequestParam BigDecimal a, @RequestParam BigDecimal b);

    @PostMapping("/subtract")
    BigDecimal subtract(@RequestParam Long userId, @RequestParam BigDecimal a, @RequestParam BigDecimal b);

    @PostMapping("/multiply")
    BigDecimal multiply(@RequestParam Long userId, @RequestParam BigDecimal a, @RequestParam BigDecimal b);

    @PostMapping("/divide")
    OperationResponseDTO divide(@RequestParam Long userId, @RequestParam BigDecimal a, @RequestParam BigDecimal b);

    @PostMapping("/sqrt")
    OperationResponseDTO squareRoot(@RequestParam Long userId, @RequestParam BigDecimal a);

    @PostMapping("/random-string")
     String randomString(@RequestParam Long userId);
}
