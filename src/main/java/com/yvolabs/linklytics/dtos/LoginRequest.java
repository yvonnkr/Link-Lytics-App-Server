package com.yvolabs.linklytics.dtos;

import lombok.Builder;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 08/01/2025
 */

@Builder
public record LoginRequest(
        String email,
        String password
) {
}
