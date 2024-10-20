package com.challenge.operations.controller;

import com.challenge.operations.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/add-balance")
    public ResponseEntity<String> addBalance(@RequestParam Long userId, @RequestParam BigDecimal amount) {
        userService.addBalance(userId, amount);
        return ResponseEntity.ok("Balance added successfully!");
    }
}
