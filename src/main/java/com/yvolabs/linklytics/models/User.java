package com.yvolabs.linklytics.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 07/01/2025
 */

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String username;
    private String password;
    private String role = "ROLE_USER";

    @OneToMany(mappedBy = "user")
    List<UrlMapping> urlMappings;


}
