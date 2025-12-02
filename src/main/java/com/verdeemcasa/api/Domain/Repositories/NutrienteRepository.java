package com.verdeemcasa.api.Domain.Repositories;

import VerdeEmCasa.Domain.Models.Nutriente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NutrienteRepository extends JpaRepository<Nutriente, Long> {

    // Essential for the frontend "Tabs"
    List<Nutriente> findByPlantId(Long plantId);
}