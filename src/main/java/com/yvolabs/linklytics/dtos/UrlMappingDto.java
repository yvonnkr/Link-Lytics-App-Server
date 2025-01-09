package com.yvolabs.linklytics.dtos;

import lombok.Builder;

import java.time.LocalDateTime;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 09/01/2025
 */

@Builder
public record UrlMappingDto(
        Long id,
        String originalUrl,
        String shortUrl,
        int clickCount,
        LocalDateTime createdDate,
        String username
) {
}
