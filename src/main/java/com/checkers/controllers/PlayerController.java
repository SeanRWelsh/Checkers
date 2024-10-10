package com.checkers.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.checkers.models.Player;
import com.checkers.repositories.PlayerRepository;
import com.checkers.securityConfiguration.SecurityUser;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
            .getContextHolderStrategy();
    private final PasswordEncoder passwordEncoder;
    private final PlayerRepository playerRepository;

    public PlayerController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
            PlayerRepository playerRepository) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.playerRepository = playerRepository;
    }

    /*
     * receives a login request from front end
     * 1. create an empty SecurityContext. should create a new SecurityContext
     * instance instead of using
     * SecurityContextHolder.getContext().setAuthentication(authentication) to avoid
     * race conditions across multiple
     * threads.
     * 2. create a new Authentication object. Spring Security does not care what
     * type of Authentication implementation is
     * set on the SecurityContext. Here, we use UsernamePasswordAuthenticationToken
     * to represent a username and password
     * authentication token.
     * .unauthenticated() sets the isAuthenticated flag to false.
     * 3. create another authentication object. this time use our custom
     * authenticationManager(defined in security) and
     * pass in the authentication request.
     * 4. Finally, set the SecurityContext on the SecurityContextHolder. Spring
     * Security uses this information for
     * authorization.
     */

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request,
            HttpServletResponse response) {
        Map<String, String> responseBody = new HashMap<>();
        try {
            UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(
                    loginRequest.username(), loginRequest.password());

            Authentication authentication = this.authenticationManager.authenticate(token);
            SecurityContext context = this.securityContextHolderStrategy.createEmptyContext();
            context.setAuthentication(authentication);
            this.securityContextHolderStrategy.setContext(context);
            securityContextRepository.saveContext(context, request, response);

            responseBody.put("message", "User " + loginRequest.username + " successfully logged in");
            responseBody.put("user", loginRequest.username);

            return responseBody;

        } catch (BadCredentialsException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            responseBody.put("error", "Login failed for user " + loginRequest.username() + ": " + e.getMessage());
            return responseBody;

        }

    }

    @PostMapping("/signup")
    public Map<String, String> signUp(@RequestBody Player player, HttpServletRequest request,
            HttpServletResponse response) {
        LoginRequest loginAfterSignup = new LoginRequest(player.getUsername(), player.getPassword());
        player.setPassword(passwordEncoder.encode(player.getPassword()));
        playerRepository.save(player);
        return login(loginAfterSignup, request, response);
    }

    // The @AuthenticationPrincipal annotation simplifies accessing the currently
    // authenticated user's
    // principal in your controllers.

    // It works by leveraging the SecurityContext stored in the session, identified
    // by the JSESSIONID cookie.

    // When you call @AuthenticationPrincipal, Spring effectively extracts the
    // principal using:

    // SecurityContext context = SecurityContextHolder.getContext();
    // Authentication authentication = context.getAuthentication();
    // Object principal = authentication.getPrincipal();

    // This principal is then passed into the method parameter.
    @GetMapping()
    public Map<String, String> getCurrentUser(@AuthenticationPrincipal SecurityUser user,
            HttpServletResponse response) {
        Map<String, String> responseBody = new HashMap<>();
        try {
            responseBody.put("username", user.getUsername());
        } catch (NullPointerException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseBody.put("error", "no user");
        }
        return responseBody;
    }

    public record LoginRequest(String username, String password) {
    }

    public record SignupRequest(String name, String username, String email, String password, String confirmPassword) {
    }

}