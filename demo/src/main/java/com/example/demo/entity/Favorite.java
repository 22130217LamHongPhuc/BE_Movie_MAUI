package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "favorites")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer userId;
    private String slug;
    private Instant createdAt = Instant.now();

    public Favorite(){}
    public String getSlug() {
        return slug;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getUserId() {
        return userId;
    }

    public Long getId() {
        return id;
    }

    // getters / setters
}
