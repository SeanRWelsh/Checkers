package com.checkers.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /* receives a login request from front end
    1. create an empty SecurityContext. should create a new SecurityContext instance instead of using SecurityContextHolder.getContext().setAuthentication(authentication) to avoid race conditions across multiple threads.
	2. create a new Authentication object. Spring Security does not care what type of Authentication implementation is set on the SecurityContext. Here, we use UsernamePasswordAuthenticationToken to represent a username and password authentication token.
	   .unauthenticated() sets the isAuthenticated flag to false.
	3. create another authentication object. this time use our custom authenticationManager(defined in security) and pass in the authentication request.
	4. Finally, set the SecurityContext on the SecurityContextHolder. Spring Security uses this information for authorization.*/

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(), loginRequest.password());
        Authentication authenticationResponse =
                this.authenticationManager.authenticate(authenticationRequest);
        context.setAuthentication(authenticationResponse);

        SecurityContextHolder.setContext(context);
        // ...

    }

    public record LoginRequest(String username, String password) {
    }

}