package com.yvolabs.linklytics.dtos;

import lombok.Builder;

import java.time.LocalDate;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 09/01/2025
 */

@Builder
public record ClickEventDTO(
         LocalDate clickDate,
         Long count
) {
}
