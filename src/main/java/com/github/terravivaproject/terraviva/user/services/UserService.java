package com.github.terravivaproject.terraviva.user.services;

import com.github.terravivaproject.terraviva.exceptions.EntityDoesNotExist;
import com.github.terravivaproject.terraviva.exceptions.UnauthenticatedException;
import com.github.terravivaproject.terraviva.resources.ErrorMessagesService;
import com.github.terravivaproject.terraviva.social.entities.dto.TagDto;
import com.github.terravivaproject.terraviva.social.entities.dto.TagRto;
import com.github.terravivaproject.terraviva.social.entities.mappers.TagMapper;
import com.github.terravivaproject.terraviva.user.entities.AppUser;
import com.github.terravivaproject.terraviva.user.repositories.UserRepository;
import dev.dmgiangi.budssecurity.securitycontext.SecurityContext;
import dev.dmgiangi.budssecurity.securitycontext.UuidSecurityUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

/**
 * The type User service.
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 09 09 2022
 */
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * persistUser.
     *
     * @param appUser a {@link com.github.terravivaproject.terraviva.user.entities.AppUser} object
     * @return a {@link com.github.terravivaproject.terraviva.user.entities.AppUser} object
     */
    public AppUser persistUser(AppUser appUser) {
        return userRepository.save(appUser);
    }

    /**
     * existsUserByUsername.
     *
     * @param username a {@link java.lang.String} object
     * @return a boolean
     */
    public boolean existsUserByUsername(String username) {
        return userRepository.existsUserByUsername(username);
    }

    /**
     * existsUserByEmail.
     *
     * @param email a {@link java.lang.String} object
     * @return a boolean
     */
    public boolean existsUserByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    /**
     * getUserById.
     *
     * @param id a {@link java.util.UUID} object
     * @return a {@link com.github.terravivaproject.terraviva.user.entities.AppUser} object
     */
    public AppUser getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExist(ErrorMessagesService.resourceNotExist()));
    }

    /**
     * getAuthenticatedUser.
     *
     * @return a {@link com.github.terravivaproject.terraviva.user.entities.AppUser} object
     */
    public AppUser getAuthenticatedUser() {
        UuidSecurityUser owner = SecurityContext
                .getUser(UuidSecurityUser.class)
                .orElseThrow(() -> new UnauthenticatedException("This operation need Authentication"));

        return userRepository.findById(owner.getMainIdentifier())
                .orElseThrow(() -> new EntityDoesNotExist(ErrorMessagesService.resourceNotExist()));

        // TODO: 29/09/22 If second exception is thrown all must be logged because the severity is HIGH
    }

    public AppUser getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityDoesNotExist(ErrorMessagesService.resourceNotExist()));
    }

    public List<TagDto> getTagsFollowedBy(AppUser user) {
        return user
                .getFollowedTags()
                .stream()
                .map(TagMapper.MAP::entityToDto)
                .sorted(Comparator.comparing(TagRto::getName))
                .toList();
    }
}
