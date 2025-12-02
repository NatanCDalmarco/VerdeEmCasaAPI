package com.verdeemcasa.api.Domain.Models;

import com.verdeemcasa.api.Domain.Models.Enums.PlantDifficulty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "plant")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String scientificName;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private PlantDifficulty difficulty;

    private String lightRequirements;

    private String waterFrequency;

    private Integer wateringIntervalDays;

    private boolean active = true;

    private LocalDateTime createdAt = LocalDateTime.now();
}