package com.verdeemcasa.api.Domain.Models;

import VerdeEmCasa.Domain.Models.Enums.PlantDifficulty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "plant") // Corresponds to 'planta' table in SQL
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // nome_comum

    private String scientificName; // nome_cientifico

    @Column(columnDefinition = "TEXT")
    private String description;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private PlantDifficulty difficulty; // FACIL, MEDIO, DIFICIL

    private String lightRequirements; // luz_necessaria (e.g., "Indireta", "Sombra")

    private String waterFrequency; // frequencia_rega (Text description)

    // Crucial for the algorithm: How many days between watering?
    private Integer wateringIntervalDays;

    private boolean active = true;

    private LocalDateTime createdAt = LocalDateTime.now();
}