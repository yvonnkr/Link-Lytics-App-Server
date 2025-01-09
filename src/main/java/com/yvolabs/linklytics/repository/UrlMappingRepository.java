package com.yvolabs.linklytics.repository;

import com.yvolabs.linklytics.models.UrlMapping;
import com.yvolabs.linklytics.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 09/01/2025
 */

public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {
    UrlMapping findByShortUrl(String shortUrl);

    List<UrlMapping> findByUser(User user);
}
