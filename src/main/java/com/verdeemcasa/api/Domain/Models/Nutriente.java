package com.verdeemcasa.api.Domain.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nutriente") // Maps to 'nutriente' table in SQL
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nutriente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // maps to 'tipo_nutriente' (e.g., "Nitrogen", "NPK 10-10-10")

    @Column(columnDefinition = "TEXT")
    private String description;

    private String applicationFrequency; // maps to 'frequencia_aplicacao'

    private String dosage; // maps to 'dosagem'

    // Relationship: Specific nutrients for a specific Plant type
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planta_id", nullable = false)
    private Plant plant;
}