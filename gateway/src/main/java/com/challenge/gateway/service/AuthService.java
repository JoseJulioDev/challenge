package com.challenge.gateway.service;

import com.challenge.gateway.dto.AuthRequestDTO;
import com.challenge.gateway.dto.AuthResponseDTO;
import com.challenge.gateway.dto.UserDTO;
import com.challenge.gateway.infrastructure.clients.AuthenticationClient;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationClient authenticationClient;

    public AuthService(AuthenticationClient authenticationClient) {
        this.authenticationClient = authenticationClient;
    }

    public String registerUser(UserDTO userDTO) {
        return authenticationClient.registerUser(userDTO);
    }

    public AuthResponseDTO loginUsuario(AuthRequestDTO requestDTO) {
        return authenticationClient.loginUser(requestDTO);
    }
}
