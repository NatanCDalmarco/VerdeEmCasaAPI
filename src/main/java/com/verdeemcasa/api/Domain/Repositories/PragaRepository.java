package com.verdeemcasa.api.Domain.Repositories;

import VerdeEmCasa.Domain.Models.Praga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PragaRepository extends JpaRepository<Praga, Long> {
    List<Praga> findByPlantId(Long plantId);
}