package com.github.terravivaproject.terraviva.user.services;

import com.github.terravivaproject.terraviva.email.services.EmailService;
import com.github.terravivaproject.terraviva.email.services.templates.ConfirmationTemplate;
import com.github.terravivaproject.terraviva.user.entities.Confirmation;
import com.github.terravivaproject.terraviva.user.entities.User;
import com.github.terravivaproject.terraviva.user.entities.dto.RegistrationRequestDto;
import com.github.terravivaproject.terraviva.user.entities.dto.UserDto;
import com.github.terravivaproject.terraviva.user.entities.mappers.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final UserService userService;
    private final ConfirmationService confirmationService;
    private final EmailService emailService;

    @Transactional
    public UserDto register(RegistrationRequestDto request) {
        User user = UserMapper.MAP
                .registrationRequestDtoToUser(request);

        UserDto response = userService.signUpUser(user);

        Confirmation confirmation = confirmationService.saveConfirmationToken(user);

        emailService.sendMimeTo(
                user.getEmail(),
                "Confirm to Complete registration process",
                new ConfirmationTemplate(
                        user.getFirstName(),
                        user.getLastName(),
                        "http://127.0.0.1:8080/registration/confirm?token=" + confirmation.getToken()
                )
        );


        return response;
    }
}
