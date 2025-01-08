package com.yvolabs.linklytics.repository;

import com.yvolabs.linklytics.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 08/01/2025
 */


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String username);

    Optional<User> findByUsername(String name);
}
