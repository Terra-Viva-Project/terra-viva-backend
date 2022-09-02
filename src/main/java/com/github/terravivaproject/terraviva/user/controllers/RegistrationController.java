package com.github.terravivaproject.terraviva.user.controllers;

import com.github.terravivaproject.terraviva.user.entities.dto.RegistrationRequestDto;
import com.github.terravivaproject.terraviva.user.entities.dto.UserDto;
import com.github.terravivaproject.terraviva.user.services.ConfirmationService;
import com.github.terravivaproject.terraviva.user.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {
    public static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    private RegistrationService registrationService;
    private ConfirmationService confirmationService;

    @PostMapping
    public ResponseEntity<UserDto> register(@Valid @RequestBody RegistrationRequestDto request) {
        UserDto registered = registrationService.register(request);
        return new ResponseEntity<>(
                registered,
                HttpStatus.CREATED
        );
    }

    @GetMapping("confirm")
    public ResponseEntity<Void> confirmRegistration(@RequestParam() UUID token) {
        confirmationService.confirm(token);
        return ResponseEntity.ok().body(null);
    }

}
