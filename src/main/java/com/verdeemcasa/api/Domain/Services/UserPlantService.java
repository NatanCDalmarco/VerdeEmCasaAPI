package com.verdeemcasa.api.Domain.Services;

import VerdeEmCasa.Application.DTOs.UserPlantRequestDto;
import VerdeEmCasa.Application.DTOs.UserPlantResponseDto;
import VerdeEmCasa.Domain.Models.Plant;
import VerdeEmCasa.Domain.Models.User;
import VerdeEmCasa.Domain.Models.UserPlant;
import VerdeEmCasa.Domain.Models.Enums.WateringStatus;
import VerdeEmCasa.Domain.Repositories.PlantRepository;
import VerdeEmCasa.Domain.Repositories.UserPlantRepository;
import VerdeEmCasa.Domain.Repositories.UserRepository;
import VerdeEmCasa.Infra.Mapper.UserPlantMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserPlantService {

    private final UserPlantRepository userPlantRepository;
    private final PlantRepository plantRepository;
    private final UserRepository userRepository;
    private final UserPlantMapper userPlantMapper;

    public UserPlantService(UserPlantRepository userPlantRepository, PlantRepository plantRepository, UserRepository userRepository, UserPlantMapper userPlantMapper) {
        this.userPlantRepository = userPlantRepository;
        this.plantRepository = plantRepository;
        this.userRepository = userRepository;
        this.userPlantMapper = userPlantMapper;
    }

    @Transactional
    public UserPlantResponseDto addPlantToUser(Long userId, UserPlantRequestDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Plant catalogPlant = plantRepository.findById(dto.plantId())
                .orElseThrow(() -> new RuntimeException("Plant not found in catalog"));

        UserPlant userPlant = userPlantMapper.toEntity(dto);
        userPlant.setUser(user);
        userPlant.setPlant(catalogPlant);

        // Inicialização: Assume que foi regada agora ao plantar
        performWateringLogic(userPlant, catalogPlant.getWateringIntervalDays());

        return userPlantMapper.toResponse(userPlantRepository.save(userPlant));
    }

    @Transactional
    public UserPlantResponseDto waterPlant(Long id) {
        UserPlant userPlant = userPlantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Plant not found"));

        // Recalcular datas
        performWateringLogic(userPlant, userPlant.getPlant().getWateringIntervalDays());

        return userPlantMapper.toResponse(userPlantRepository.save(userPlant));
    }

    public List<UserPlantResponseDto> getUserPlants(Long userId) {
        // Aqui poderíamos adicionar lógica para atualizar o status (Pendente/Atrasado) se a data passou
        List<UserPlant> plants = userPlantRepository.findByUserId(userId);

        // Atualiza status dinamicamente antes de devolver (Opcional, mas recomendado)
        plants.forEach(this::checkStatus);
        userPlantRepository.saveAll(plants); // Salva atualizações de status

        return plants.stream()
                .map(userPlantMapper::toResponse)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        userPlantRepository.deleteById(id);
    }

    // --- Métodos Auxiliares ---

    private void performWateringLogic(UserPlant userPlant, Integer intervalDays) {
        LocalDateTime now = LocalDateTime.now();
        userPlant.setLastWatered(now);

        // Calcula próxima rega baseada no intervalo do catálogo
        if (intervalDays != null && intervalDays > 0) {
            userPlant.setNextWatering(now.plusDays(intervalDays));
        } else {
            // Padrão se não houver info: 3 dias
            userPlant.setNextWatering(now.plusDays(3));
        }

        userPlant.setWateringStatus(WateringStatus.DONE);
    }

    private void checkStatus(UserPlant plant) {
        if (plant.getNextWatering().isBefore(LocalDateTime.now())) {
            plant.setWateringStatus(WateringStatus.LATE); // Atrasada
        } else if (plant.getNextWatering().isBefore(LocalDateTime.now().plusHours(12))) {
            plant.setWateringStatus(WateringStatus.PENDING); // Quase na hora
        }
    }
}