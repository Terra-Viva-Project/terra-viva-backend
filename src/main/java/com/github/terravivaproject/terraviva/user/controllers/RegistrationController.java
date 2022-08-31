package com.github.terravivaproject.terraviva.user.controllers;

import com.github.terravivaproject.terraviva.user.entities.dto.RegistrationRequestDto;
import com.github.terravivaproject.terraviva.user.entities.dto.UserDto;
import com.github.terravivaproject.terraviva.user.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {
    public static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    private RegistrationService registrationService;

    @PostMapping
    public UserDto register(@Valid @RequestBody RegistrationRequestDto request) {
        return registrationService.register(request);
    }

}
