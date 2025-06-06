package com.springboot.bakefinity.model.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class UserDTO {
    private Integer id;
    private String name;
    private String username;
    private String phoneNumber;
    private String email;
    private String password;
    private double creditLimit;
    private Date birthDate;
    private String job;
    private LocalDateTime createdAt;
    String role;
    private List<String> authorities;
    public UserDTO() {
    }

    public UserDTO(String name, String username, String phoneNumber, String email, String password, double creditLimit, Date birthDate, String job, LocalDateTime createdAt) {
        this.name = name;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.creditLimit = creditLimit;
        this.birthDate = birthDate;
        this.job = job;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + 
                ", username='" + username +
                ", phoneNumber='" + phoneNumber +
                ", email='" + email + 
                ", password='" + password + 
                ", creditLimit=" + creditLimit +
                ", birthDate=" + birthDate +
                ", job='" + job +  
                ", createdAt=" + createdAt +
                ", role=" + role +
                '}';
    }
}
