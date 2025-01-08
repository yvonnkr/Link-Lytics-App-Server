package com.yvolabs.linklytics.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.Set;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 08/01/2025
 */

@Builder
public record RegisterRequest(
        @NotEmpty(message = "username should not be empty")
        @Size(min = 3, max = 50, message = "username should be 3 to 50 characters long")
        String username,

        @NotEmpty(message = "username should not be empty")
        @Pattern(regexp = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "email is invalid")
        String email,

        Set<String> role,


        @NotEmpty(message = "password should not be empty")
        @Size(min = 8, max = 20, message = "password should be 8 to 20 characters long")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
                message = "password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")
        String password
) {
}
