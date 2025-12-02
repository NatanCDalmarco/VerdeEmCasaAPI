package com.verdeemcasa.api.Domain.Repositories;

import com.verdeemcasa.api.Domain.Models.UserPlant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPlantRepository extends JpaRepository<UserPlant, Long> {

    List<UserPlant> findByUserId(Long userId);
}