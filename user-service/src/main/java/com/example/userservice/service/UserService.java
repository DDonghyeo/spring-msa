package com.example.userservice.service;

import com.example.userservice.domain.User;
import com.example.userservice.dto.UserRequestDto;
import com.example.userservice.dto.UserResponseDto;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    public void register(UserRequestDto.Register request) {
        userRepository.save(request.toEntity());
    }
}
