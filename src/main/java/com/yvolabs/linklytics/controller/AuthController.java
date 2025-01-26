package com.yvolabs.linklytics.controller;

import com.yvolabs.linklytics.dtos.LoginRequest;
import com.yvolabs.linklytics.dtos.RegisterRequest;
import com.yvolabs.linklytics.models.User;
import com.yvolabs.linklytics.security.jwt.JwtAuthenticationResponse;
import com.yvolabs.linklytics.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 08/01/2025
 */

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/public/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.username());
        user.setPassword(registerRequest.password());
        user.setEmail(registerRequest.email());
        user.setRole("ROLE_USER");
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully with username: " + registeredUser.getUsername());
    }

    @PostMapping("/public/login")
    public ResponseEntity<JwtAuthenticationResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        JwtAuthenticationResponse response = userService.authenticateUser(loginRequest);
        return ResponseEntity.ok(response);
    }

}
