package com.github.terravivaproject.terraviva.user.services;

import com.github.terravivaproject.terraviva.exceptions.EntityDoesNotExist;
import com.github.terravivaproject.terraviva.resources.ErrorMessagesService;
import com.github.terravivaproject.terraviva.user.entities.AppUser;
import com.github.terravivaproject.terraviva.user.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    public AppUser persistUser(AppUser appUser) {
        return userRepository.save(appUser);
    }

    public boolean existsUserByUsername(String username) {
        return userRepository.existsUserByUsername(username);
    }

    public boolean existsUserByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    public AppUser getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExist(ErrorMessagesService.resourceNotExist()));
    }
}
