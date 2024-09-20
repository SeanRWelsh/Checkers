package com.checkers.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {
    @GetMapping("/checkSession")
    public ResponseEntity<String> checkSession(HttpSession session) {
        SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        if (securityContext != null && securityContext.getAuthentication() != null) {
            return ResponseEntity.ok("Authenticated user: " + securityContext.getAuthentication().getName());
        }
        return ResponseEntity.ok("No authenticated user found.");
    }
}
