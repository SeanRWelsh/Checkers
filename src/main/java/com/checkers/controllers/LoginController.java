package com.checkers.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class LoginController {
    @GetMapping("/login")
    ResponseEntity<String> login() {
        System.out.println("in login...wtf");
        return ResponseEntity.status(HttpStatus.OK).body("at login");
    }
}