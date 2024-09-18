package com.checkers.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /* receives a login request from front end
    1. create an empty SecurityContext. should create a new SecurityContext instance instead of using
    SecurityContextHolder.getContext().setAuthentication(authentication) to avoid race conditions across multiple
    threads.
	2. create a new Authentication object. Spring Security does not care what type of Authentication implementation is
	set on the SecurityContext. Here, we use UsernamePasswordAuthenticationToken to represent a username and password
	authentication token.
	   .unauthenticated() sets the isAuthenticated flag to false.
	3. create another authentication object. this time use our custom authenticationManager(defined in security) and
	pass in the authentication request.
	4. Finally, set the SecurityContext on the SecurityContextHolder. Spring Security uses this information for
	authorization.*/

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(
                loginRequest.username(), loginRequest.password());

        Authentication authentication = this.authenticationManager.authenticate(token);
        SecurityContext context = this.securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(authentication);
        this.securityContextHolderStrategy.setContext(context);
        securityContextRepository.saveContext(context, request, response);

        //Must change this return

        return ResponseEntity.ok("User " + loginRequest.username + " successfully logged in");
                //this.securityContextHolderStrategy.getContext();

    }

    public record LoginRequest(String username, String password) {
    }

}