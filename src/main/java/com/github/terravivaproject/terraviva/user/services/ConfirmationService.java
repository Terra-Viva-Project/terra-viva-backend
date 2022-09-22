package com.github.terravivaproject.terraviva.user.services;

import com.github.terravivaproject.terraviva.exceptions.ConfirmationJustExpiredException;
import com.github.terravivaproject.terraviva.exceptions.EntityDoesNotExist;
import com.github.terravivaproject.terraviva.resources.ErrorMessagesService;
import com.github.terravivaproject.terraviva.user.entities.AppUser;
import com.github.terravivaproject.terraviva.user.entities.Confirmation;
import com.github.terravivaproject.terraviva.user.repositories.ConfirmationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Confirmation service.
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 09 09 2022
 */
@Service
@AllArgsConstructor
public class ConfirmationService {
    private final ConfirmationRepository confirmationRepository;
    private final UserService userService;

    /**
     * Create new confirmation token confirmation.
     *
     * @param appUser the User
     * @return the confirmation Token
     */
    public Confirmation createNewConfirmationToken(AppUser appUser) {
        //Create a new Confirmation Token
        return confirmationRepository.save(
                new Confirmation()
                        .setAppUser(appUser)
        );
    }

    /**
     * Verify the user and remove the old token
     *
     * @param userToken the user token
     */
    public void verifyUser(UUID userToken) {
        //GetToken and Check if it's exist
        Confirmation confirmation =
                confirmationRepository.findById(userToken)
                        .orElseThrow(
                                () -> new EntityDoesNotExist(ErrorMessagesService.confirmationTokenNotExist())
                        );

        //check if token is not expired
        if (confirmation.getExpirationTime().isBefore(LocalDateTime.now()))
            throw new ConfirmationJustExpiredException(
                    ErrorMessagesService.expirationPassed());

        //Set user as verified
        userService.persistUser(
                confirmation
                        .getAppUser()
                        .setVerified(true)
        );

        //Delete confirmationToken
        confirmationRepository.delete(confirmation);
    }
}
