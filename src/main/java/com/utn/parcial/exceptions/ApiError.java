package com.utn.parcial.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class ApiError {
    private String message;
    private HttpStatus status;
    private List<String> errors;

    public ApiError(String message, HttpStatus status, List<String> errors) {
        this.message = message;
        this.status = status;
        this.errors = errors;
    }
}
