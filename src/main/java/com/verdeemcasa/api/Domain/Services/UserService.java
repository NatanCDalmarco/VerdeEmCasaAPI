package com.verdeemcasa.api.Domain.Services;

import com.verdeemcasa.api.Application.DTOs.UserRequestDto;
import com.verdeemcasa.api.Application.DTOs.UserResponseDto;
import com.verdeemcasa.api.Domain.Models.User;
import com.verdeemcasa.api.Domain.Repositories.UserRepository;
import com.verdeemcasa.api.Infra.Exception.ResourceNotFoundException;
import com.verdeemcasa.api.Infra.Mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto create(UserRequestDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.password()));
        user.setActive(true);

        user = userRepository.save(user);

        return userMapper.toResponse(user);
    }

    public List<UserResponseDto> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponseDto getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário inexistente"));
        return userMapper.toResponse(user);
    }

    public UserDetails getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}