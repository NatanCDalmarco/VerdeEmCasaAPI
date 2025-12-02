package com.verdeemcasa.api.Domain.Repositories;

import VerdeEmCasa.Domain.Models.UserPlant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPlantRepository extends JpaRepository<UserPlant, Long> {

    // Busca todas as plantas de um usuário específico
    List<UserPlant> findByUserId(Long userId);

    // Opcional: Buscar plantas que precisam ser regadas (para notificações futuras)
    // List<UserPlant> findByNextWateringBefore(LocalDateTime date);
}