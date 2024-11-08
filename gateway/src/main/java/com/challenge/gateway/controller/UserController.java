package com.challenge.gateway.controller;

import com.challenge.gateway.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getBalance(@RequestParam Long userId) {
        return ResponseEntity.ok(userService.getBalance(userId));
    }
}
