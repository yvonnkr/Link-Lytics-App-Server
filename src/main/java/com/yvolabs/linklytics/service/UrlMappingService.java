package com.yvolabs.linklytics.service;

import com.yvolabs.linklytics.dtos.ClickEventDTO;
import com.yvolabs.linklytics.dtos.UrlMappingDto;
import com.yvolabs.linklytics.models.ClickEvent;
import com.yvolabs.linklytics.models.UrlMapping;
import com.yvolabs.linklytics.models.User;
import com.yvolabs.linklytics.repository.ClickEventRepository;
import com.yvolabs.linklytics.repository.UrlMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 09/01/2025
 */

@Service
@RequiredArgsConstructor
public class UrlMappingService {

    private final UrlMappingRepository urlMappingRepository;
    private final ClickEventRepository clickEventRepository;

    public UrlMappingDto createShortUrl(String originalUrl, User user) {
        String shortUrl = generateShortUrl();

        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setShortUrl(shortUrl);
        urlMapping.setCreatedDate(LocalDateTime.now());
        urlMapping.setUser(user);

        UrlMapping savedUrlMapping = urlMappingRepository.save(urlMapping);
        return convertToDto(savedUrlMapping);
    }

    public List<UrlMappingDto> getUrlsByUser(User user) {
        List<UrlMapping> allByUser = urlMappingRepository.findByUser(user);
        return allByUser.stream()
                .map(this::convertToDto)
                .toList();
    }

    public List<ClickEventDTO> getClickEventsByDate(String shortUrl, LocalDateTime start, LocalDateTime end) {
        UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl);

        if (urlMapping != null) {
            return clickEventRepository.findByUrlMappingAndClickDateBetween(urlMapping, start, end).stream()
                    .collect(Collectors.groupingBy(click -> click.getClickDate().toLocalDate(), Collectors.counting()))
                    .entrySet().stream()
                    .map(entry -> ClickEventDTO.builder()
                            .clickDate(entry.getKey())
                            .count(entry.getValue())
                            .build())
                    .toList();
        }

        return null;
    }

    public Map<LocalDate, Long> getTotalClicksByUserAndDate(User user, LocalDate start, LocalDate end) {
        List<UrlMapping> urlMappings = urlMappingRepository.findByUser(user);
        List<ClickEvent> clickEvents = clickEventRepository.findByUrlMappingInAndClickDateBetween(urlMappings, start.atStartOfDay(), end.plusDays(1).atStartOfDay());
        return clickEvents.stream()
                .collect(Collectors.groupingBy(click -> click.getClickDate().toLocalDate(), Collectors.counting()));

    }


    private UrlMappingDto convertToDto(UrlMapping urlMapping) {
        return UrlMappingDto.builder()
                .id(urlMapping.getId())
                .originalUrl(urlMapping.getOriginalUrl())
                .shortUrl(urlMapping.getShortUrl())
                .createdDate(urlMapping.getCreatedDate())
                .clickCount(urlMapping.getClickCount())
                .createdDate(urlMapping.getCreatedDate())
                .username(urlMapping.getUser().getUsername())
                .build();
    }

    private String generateShortUrl() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder shortUrl = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            shortUrl.append(characters.charAt(random.nextInt(characters.length())));
        }
        return shortUrl.toString();
    }


}
