package com.challenge.authentication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
    private String userName;

    @NotBlank(message = "Password cannot be blank")
    @Size(max = 10, message = "The password must be less than 10 characters")
    private String password;

    public UserDTO() {
    }

    public UserDTO(String password, String userName) {
        this.password = password;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}