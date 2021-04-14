package com.utn.parcial.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestResponseEntityHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolations(ConstraintViolationException ex){
        List<String> errors=new ArrayList<>();
        for (ConstraintViolation<?> v: ex.getConstraintViolations()){
            errors.add(v.getRootBeanClass().getName()+ " "+ v.getPropertyPath()+ " "+ ": "+v.getMessage());
        }
        ApiError apiError=new ApiError(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST,errors);
        return new ResponseEntity<>(apiError,new HttpHeaders(),apiError.getStatus());
    }
}
