package com.verdeemcasa.api.Application.Controllers;

import com.verdeemcasa.api.Application.DTOs.UserPlantRequestDto;
import com.verdeemcasa.api.Application.DTOs.UserPlantResponseDto;
import com.verdeemcasa.api.Domain.Services.UserPlantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/my-plants")
public class UserPlantController {

    private final UserPlantService userPlantService;

    public UserPlantController(UserPlantService userPlantService) {
        this.userPlantService = userPlantService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserPlantResponseDto>> getMyPlants(@PathVariable Long userId) {
        return ResponseEntity.ok(userPlantService.getUserPlants(userId));
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<UserPlantResponseDto> addPlant(
            @PathVariable Long userId,
            @RequestBody @Valid UserPlantRequestDto data,
            UriComponentsBuilder uriBuilder
    ) {
        var plant = userPlantService.addPlantToUser(userId, data);
        var uri = uriBuilder.path("/my-plants/{id}").buildAndExpand(plant.id()).toUri();
        return ResponseEntity.created(uri).body(plant);
    }

    @PatchMapping("/{id}/water")
    public ResponseEntity<UserPlantResponseDto> waterPlant(@PathVariable Long id) {
        return ResponseEntity.ok(userPlantService.waterPlant(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removePlant(@PathVariable Long id) {
        userPlantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}