package com.challenge.gateway.service;


import com.challenge.gateway.infrastructure.clients.UserClient;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class UserService {

    private final UserClient userClient;

    public UserService(UserClient userClient) {
        this.userClient = userClient;
    }

    public String addBalance(Long userId,BigDecimal amount) {
        return userClient.addBalance(userId, amount);
    }
}
