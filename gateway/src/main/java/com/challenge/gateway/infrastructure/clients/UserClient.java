package com.challenge.gateway.infrastructure.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name="userClient", url="http://localhost:8082/api/users")
public interface UserClient {

    @PostMapping("/add-balance")
    public String addBalance(@RequestParam Long userId, @RequestParam BigDecimal amount);

}
