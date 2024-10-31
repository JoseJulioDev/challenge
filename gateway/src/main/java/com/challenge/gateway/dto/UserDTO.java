package com.challenge.gateway.dto;

public class UserDTO {

    private String userName;
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
