package com.github.terravivaproject.terraviva.user.controllers;

import com.github.terravivaproject.terraviva.resources.DeployDataService;
import com.github.terravivaproject.terraviva.user.entities.AppUser;
import com.github.terravivaproject.terraviva.user.entities.dto.RegistrationRequestDto;
import com.github.terravivaproject.terraviva.user.entities.dto.UserDto;
import com.github.terravivaproject.terraviva.user.entities.mappers.UserMapper;
import com.github.terravivaproject.terraviva.user.services.ConfirmationService;
import com.github.terravivaproject.terraviva.user.services.RegistrationService;
import dev.dmgiangi.budssecurity.authorizzation.annotations.Public;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

/**
 * The type Registration controller.
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 09 09 2022
 */
@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private final ConfirmationService confirmationService;
    private final DeployDataService deployData;

    /**
     * Register response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @Public
    @PostMapping
    public ResponseEntity<UserDto> register(@Valid @RequestBody RegistrationRequestDto request) {
        //Register the user
        AppUser appUser = registrationService.register(request);

        //Map the user entity to userDto
        UserDto registeredUser =
                UserMapper.MAP.userToUserDto(appUser);

        //Return a 201 response with the user data and the uri of the resource
        return ResponseEntity
                .created(
                        URI.create(
                                ServletUriComponentsBuilder
                                        .fromCurrentContextPath()
                                        .path("users/" + registeredUser.getId()
                                                .toString())
                                        .toUriString()
                        )
                )
                .body(registeredUser);
    }

    /**
     * Confirm registration response entity.
     *
     * @param token the token
     * @return the response entity
     */
    @Public
    @GetMapping("confirm")
    public ResponseEntity<Void> confirmRegistration(@RequestParam UUID token) {
        //Verify the user with the given token
        confirmationService.verifyUser(token);

        //Return a 200 response
        return ResponseEntity.ok().body(null);
    }

}
