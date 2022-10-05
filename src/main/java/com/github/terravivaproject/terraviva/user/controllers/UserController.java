package com.github.terravivaproject.terraviva.user.controllers;

import com.github.terravivaproject.terraviva.exceptions.model.ErrorDto;
import com.github.terravivaproject.terraviva.social.entities.dto.TagDto;
import com.github.terravivaproject.terraviva.user.entities.dto.UserDto;
import com.github.terravivaproject.terraviva.user.entities.mappers.UserMapper;
import com.github.terravivaproject.terraviva.user.repositories.UserRepository;
import com.github.terravivaproject.terraviva.user.services.UserService;
import dev.dmgiangi.budssecurity.authorizations.annotations.Public;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * UserController class.
 *
 * @author giangi
 * @version $Id: $Id
 */
@RestController
@RequestMapping("users")
@AllArgsConstructor
@Tag(name = "User", description = "User Related Endpoint")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    // TODO: 23/09/22 return different information if the request come from the same user

    /**
     * getUserById.
     *
     * @param username a {@link java.lang.String} object
     * @return a {@link com.github.terravivaproject.terraviva.user.entities.dto.UserDto} object
     */
    @Public
    @GetMapping("{username}")
    @Operation(
            summary = "get a user",
            description = "get information about a single user by username of the user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "user information",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDto.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User does not exists",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class))),
            })
    public UserDto getUserById(@PathVariable @Schema(example = "andrea") String username) {
        return UserMapper.MAP.userToUserDto(
                userService.getUserByUsername(username)
        );
    }

    /**
     * <p>getFollowedTags.</p>
     *
     * @param username a {@link java.lang.String} object
     * @return a {@link java.util.List} object
     */
    @Public
    @GetMapping("/{username}/followed-tags")
    @Operation(
            summary = "get tag followed by a user",
            description = "get the list of tag that a user follows",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Followed Tag",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(
                                            arraySchema = @Schema(implementation = List.class),
                                            schema = @Schema(implementation = TagDto.class)))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User does not exists",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorDto.class)))
            })
    public List<TagDto> getFollowedTags(@PathVariable @Schema(example = "andrea") String username) {
        userService.getUserByUsername(username);
        return userService.getTagsFollowedBy(
                userService.getUserByUsername(username)
        );
    }
}
