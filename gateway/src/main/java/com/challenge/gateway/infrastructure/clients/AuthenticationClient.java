package com.challenge.gateway.infrastructure.clients;

import com.challenge.gateway.dto.AuthRequestDTO;
import com.challenge.gateway.dto.UserDTO;
import com.challenge.gateway.dto.AuthResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value="authClient", url = "localhost:8081/api/auth")
public interface AuthenticationClient {

    @PostMapping("/register")
    AuthResponseDTO registerUser(@RequestBody UserDTO userDTO);

    @PostMapping("/login")
    AuthResponseDTO loginUser(@RequestBody AuthRequestDTO requestDTO);
}
