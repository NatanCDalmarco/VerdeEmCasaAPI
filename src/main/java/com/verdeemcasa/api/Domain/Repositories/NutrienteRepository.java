package com.verdeemcasa.api.Domain.Repositories;

import com.verdeemcasa.api.Domain.Models.Nutriente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NutrienteRepository extends JpaRepository<Nutriente, Long> {
    List<Nutriente> findByPlantId(Long plantId);
}