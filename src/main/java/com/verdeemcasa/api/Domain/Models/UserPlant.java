package com.verdeemcasa.api.Domain.Models;

import VerdeEmCasa.Domain.Models.Enums.WateringStatus;
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

    // Personalização do Usuário
    private String nickname; // "apelido" (ex: Minha Jiboia)
    private String location; // "local" (ex: Sala)
    private String customPhotoUrl; // foto personalizada

    // Controle de Rega
    private LocalDateTime lastWatered; // "ultima_rega"
    private LocalDateTime nextWatering; // "proxima_rega" (Calculado)

    @Enumerated(EnumType.STRING)
    private WateringStatus wateringStatus; // status_rega

    private LocalDateTime addedAt = LocalDateTime.now();

    // Relacionamentos
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planta_id", nullable = false)
    private Plant plant; // Referência ao catálogo para pegar info científica
}