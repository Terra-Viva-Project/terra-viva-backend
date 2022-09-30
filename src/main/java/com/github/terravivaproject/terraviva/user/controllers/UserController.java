package com.github.terravivaproject.terraviva.user.controllers;

import com.github.terravivaproject.terraviva.social.entities.dto.TagDto;
import com.github.terravivaproject.terraviva.user.entities.dto.UserDto;
import com.github.terravivaproject.terraviva.user.entities.mappers.UserMapper;
import com.github.terravivaproject.terraviva.user.repositories.UserRepository;
import com.github.terravivaproject.terraviva.user.services.UserService;
import dev.dmgiangi.budssecurity.authorizations.annotations.Public;
import lombok.AllArgsConstructor;
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
    public UserDto getUserById(@PathVariable String username) {
        return UserMapper.MAP.userToUserDto(
                userService.getUserByUsername(username)
        );
    }

    @Public
    @GetMapping("/{username}/followed-tags")
    public List<TagDto> getFolloweTags(@PathVariable String username) {
        userService.getUserByUsername(username);
        return userService.getTagsFollowedBy(
                userService.getUserByUsername(username)
        );
    }
}
