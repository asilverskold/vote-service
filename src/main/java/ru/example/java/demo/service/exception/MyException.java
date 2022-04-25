package ru.example.java.demo.service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class MyException extends RuntimeException {
    @Getter
    private HttpStatus status;

    public MyException(String message) {
        super(message);
    }

    public MyException(String message,HttpStatus status) {
        super(message);
        this.status = status;
    }
}
