package com.challenge.gateway.controller;

import com.challenge.gateway.dto.AuthRequestDTO;
import com.challenge.gateway.dto.UserDTO;
import com.challenge.gateway.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthService authService;

    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(authService.registerUser(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequestDTO requestDTO) {
        return ResponseEntity.ok(authService.loginUsuario(requestDTO));
    }
}
