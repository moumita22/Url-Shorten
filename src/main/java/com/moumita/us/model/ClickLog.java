package com.moumita.us.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class ClickLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shortCode;
    private String ipAddress;
    private LocalDateTime clickedAt;
}

