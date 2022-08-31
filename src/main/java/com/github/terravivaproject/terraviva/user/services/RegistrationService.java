package com.github.terravivaproject.terraviva.user.services;

import com.github.terravivaproject.terraviva.user.entities.User;
import com.github.terravivaproject.terraviva.user.entities.dto.RegistrationRequestDto;
import com.github.terravivaproject.terraviva.user.entities.dto.UserDto;
import com.github.terravivaproject.terraviva.user.entities.mappers.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final UserService userService;

    public UserDto register(RegistrationRequestDto request) {
        User user = UserMapper.MAP
                .registrationRequestDtoToUser(request);
        return userService.signUpUser(user);
    }
}
