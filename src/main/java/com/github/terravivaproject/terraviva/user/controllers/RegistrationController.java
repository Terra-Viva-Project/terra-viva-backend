package com.github.terravivaproject.terraviva.user.controllers;

import com.github.terravivaproject.terraviva.exceptions.model.ErrorDto;
import com.github.terravivaproject.terraviva.exceptions.model.MultipleErrorDto;
import com.github.terravivaproject.terraviva.resources.DeployDataService;
import com.github.terravivaproject.terraviva.user.entities.AppUser;
import com.github.terravivaproject.terraviva.user.entities.dto.RegistrationRequestDto;
import com.github.terravivaproject.terraviva.user.entities.dto.UserDto;
import com.github.terravivaproject.terraviva.user.entities.mappers.UserMapper;
import com.github.terravivaproject.terraviva.user.services.ConfirmationService;
import com.github.terravivaproject.terraviva.user.services.RegistrationService;
import dev.dmgiangi.budssecurity.authorizations.annotations.Public;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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
@Tag(name = "Registration", description = "Registration Related Endpoint")
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
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "create a user",
            description = "create a new user",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "new user created",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDto.class))),

                    @ApiResponse(
                            responseCode = "409",
                            description = "the user already exist",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = MultipleErrorDto.class))),
            })
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
    @GetMapping(value = "confirm")
    @Operation(
            summary = "confirm a user",
            description = "confirm user email by giving confirmation token",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User is verified",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDto.class))),

                    @ApiResponse(
                            responseCode = "404",
                            description = "the user does not exist",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class))),
                    @ApiResponse(
                            responseCode = "406",
                            description = "the confirmation token is just expired",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class))),
            })
    public ResponseEntity<Void> confirmRegistration(
            @RequestParam @Schema(example = "3fa85f64-5717-4562-b3fc-2c963f66afa6") UUID token) {
        //Verify the user with the given token
        confirmationService.verifyUser(token);

        //Return a 200 response
        return ResponseEntity.ok().body(null);
    }

}
