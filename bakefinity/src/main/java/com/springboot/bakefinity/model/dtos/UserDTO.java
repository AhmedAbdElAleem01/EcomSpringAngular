package com.bakefinity.model.dtos;

import java.time.LocalDateTime;
import java.util.Date;

public class UserDTO {
    private int id;
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

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
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
