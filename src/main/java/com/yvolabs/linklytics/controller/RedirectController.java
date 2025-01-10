package com.yvolabs.linklytics.controller;

import com.yvolabs.linklytics.models.UrlMapping;
import com.yvolabs.linklytics.service.UrlMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 10/01/2025
 */

@RestController
@RequiredArgsConstructor
public class RedirectController {

    private final UrlMappingService urlMappingService;

    //Example: http://localhost:8080/foFV32Dd will redirect to original url
    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl) {
        UrlMapping urlMapping = urlMappingService.getOriginalUrl(shortUrl);
        if (urlMapping != null) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HttpHeaders.LOCATION, urlMapping.getOriginalUrl());
            return ResponseEntity.status(HttpStatus.FOUND).headers(httpHeaders).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
