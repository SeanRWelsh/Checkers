package com.checkers.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final HttpSessionSecurityContextRepository securityContextRepository;

    public LoginController(AuthenticationManager authenticationManager,
            HttpSessionSecurityContextRepository securityContextRepository) {
        this.authenticationManager = authenticationManager;
        this.securityContextRepository = securityContextRepository;
    }

    @PostMapping
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        System.out.println("in LoginController");
        Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(loginRequest.username(),
                loginRequest.password());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);

        // Save the authenticated user in the SecurityContextRepository
        SecurityContextHolder.getContext().setAuthentication(authenticationResponse);
        this.securityContextRepository.saveContext(SecurityContextHolder.getContext(), request, null);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public record LoginRequest(String username, String password) {
    }

}