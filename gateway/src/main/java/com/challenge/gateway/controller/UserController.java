package com.challenge.gateway.controller;

import com.challenge.gateway.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add-balance")
    public ResponseEntity<String> addBalance(@RequestParam Long userId, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok( userService.addBalance(userId, amount));
    }
}
