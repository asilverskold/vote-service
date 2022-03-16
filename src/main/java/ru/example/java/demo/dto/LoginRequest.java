package ru.example.java.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginRequest implements Serializable {
    private final String username;
    private final String password;
}