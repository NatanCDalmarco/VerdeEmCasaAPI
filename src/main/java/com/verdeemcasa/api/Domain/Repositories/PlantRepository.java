package com.verdeemcasa.api.Domain.Repositories;

import VerdeEmCasa.Domain.Models.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

    // Supports the search bar in the Catalog page
    List<Plant> findByNameContainingIgnoreCase(String name);

    // Only show active plants in the catalog
    List<Plant> findByActiveTrue();
}