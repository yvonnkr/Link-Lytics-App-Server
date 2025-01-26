package com.yvolabs.linklytics.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 13/01/2025
 */

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/username")
    @PreAuthorize(value = "hasRole('USER')")
    public ResponseEntity<String> getUsername(Principal principal) {
        String name = principal.getName();
        return ResponseEntity.ok(name);
    }
}
