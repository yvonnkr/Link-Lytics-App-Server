package com.yvolabs.linklytics.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 15/12/2024
 */

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {
    HttpStatus status;
    String message;
    private Map<String, String> errors;
}
