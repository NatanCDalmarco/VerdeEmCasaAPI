package com.verdeemcasa.api.Domain.Models;

import com.verdeemcasa.api.Domain.Models.Enums.WateringStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "planta_usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPlant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    private String location;
    private String customPhotoUrl;

    private LocalDateTime lastWatered;
    private LocalDateTime nextWatering;

    @Enumerated(EnumType.STRING)
    private WateringStatus wateringStatus;

    private LocalDateTime addedAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planta_id", nullable = false)
    private Plant plant;
}