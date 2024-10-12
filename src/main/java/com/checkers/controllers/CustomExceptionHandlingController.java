package com.checkers.controllers;

import jakarta.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandlingController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
        Map<String, String> responseBody = new LinkedHashMap<>();

        Map<String, String> errors = e.getConstraintViolations()
                .stream()
                .collect(Collectors.toMap(violation -> violation.getPropertyPath().toString(),
                        violation -> violation.getMessage()));

        responseBody.putAll(errors);

        return ResponseEntity.badRequest().body(responseBody);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handdleDataIntegrityViolationException(DataIntegrityViolationException e) {
        Map<String, Object> responseBody = new LinkedHashMap<>();

        String errors = e.getRootCause().getMessage();
        if (errors.contains("Key (username")) {
            responseBody.put("username", "Username already exists");
        } else if (errors.contains("Detail: Key (email)")) {
            responseBody.put("email", "Email already in use");
        } else {
            responseBody.put("errors", e.getRootCause().getMessage());
        }

        return ResponseEntity.badRequest().body(responseBody);
    }
}
