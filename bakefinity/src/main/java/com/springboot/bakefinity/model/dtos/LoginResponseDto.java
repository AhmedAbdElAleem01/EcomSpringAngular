package com.springboot.bakefinity.model.dtos;

public class LoginResponseDto {
    private String token;
    private UserDTO user;

    // Constructors
    public LoginResponseDto() {}

    public LoginResponseDto(String token, UserDTO user) {
        this.token = token;
        this.user = user;
    }

    // Getters and Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public UserDTO getUser() { return user; }
    public void setUser(UserDTO user) { this.user = user; }
}
