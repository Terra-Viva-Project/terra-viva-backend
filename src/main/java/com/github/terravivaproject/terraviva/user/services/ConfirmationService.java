package com.github.terravivaproject.terraviva.user.services;

import com.github.terravivaproject.terraviva.exceptions.ConfirmationJustExpiredException;
import com.github.terravivaproject.terraviva.exceptions.EntityDoesNotExist;
import com.github.terravivaproject.terraviva.user.entities.Confirmation;
import com.github.terravivaproject.terraviva.user.entities.User;
import com.github.terravivaproject.terraviva.user.repositories.ConfirmationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConfirmationService {
    private ConfirmationRepository confirmationRepository;

    public Confirmation saveConfirmationToken(User user) {
        return confirmationRepository.save(
                new Confirmation()
                        .setUser(user)
        );
    }

    public void confirm(UUID token) {
        Optional<Confirmation> confirmation = confirmationRepository.findById(token);
        if (confirmation.isEmpty())
            throw new EntityDoesNotExist("This Confirmation token does not exist");
        if (confirmation.get().getExpirationTime().isBefore(LocalDateTime.now()))
            throw new ConfirmationJustExpiredException("the expiration date has already passed");

        confirmation.get().setConfirmationTimestamp(LocalDateTime.now());
        confirmation.get().getUser().setVerified(true);

        confirmationRepository.save(confirmation.get());
    }
}
