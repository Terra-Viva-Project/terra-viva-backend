package com.github.terravivaproject.terraviva.user.controllers;

import com.github.terravivaproject.terraviva.user.entities.dto.UserDto;
import com.github.terravivaproject.terraviva.user.entities.mappers.UserMapper;
import com.github.terravivaproject.terraviva.user.repositories.UserRepository;
import com.github.terravivaproject.terraviva.user.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("{id}")
    public UserDto getUserById(@PathVariable UUID id) {
        return UserMapper.MAP.userToUserDto(
                userService.getUserById(id)
        );
    }
}
