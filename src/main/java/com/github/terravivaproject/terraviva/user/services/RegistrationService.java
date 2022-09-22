package com.github.terravivaproject.terraviva.user.services;

import com.github.terravivaproject.terraviva.email.services.EmailService;
import com.github.terravivaproject.terraviva.email.templates.ConfirmationTemplate;
import com.github.terravivaproject.terraviva.exceptions.UserAlreadyExistsException;
import com.github.terravivaproject.terraviva.resources.DeployDataService;
import com.github.terravivaproject.terraviva.resources.ErrorMessagesService;
import com.github.terravivaproject.terraviva.user.entities.AppUser;
import com.github.terravivaproject.terraviva.user.entities.Confirmation;
import com.github.terravivaproject.terraviva.user.entities.dto.RegistrationRequestDto;
import com.github.terravivaproject.terraviva.user.entities.mappers.UserMapper;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * The Registration service.
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 09 09 2022
 */
@Service
@AllArgsConstructor
public class RegistrationService {
    private final UserService userService;
    private final ConfirmationService confirmationService;
    private final EmailService emailService;

    /**
     * Register user dto.
     *
     * @param request a RegistrationRequestDto
     * @return the UserDto
     */
    @Transactional
    public AppUser register(RegistrationRequestDto request) {
        //check if username or email already exist
        checkUserUniqueness(request.getUsername(), request.getEmail());

        //Encrypt the password
        request.setPassword(
                BCrypt.hashpw(request.getPassword(), BCrypt.gensalt())
        );

        //Map the registration request to the user entity
        AppUser appUser = UserMapper.MAP
                .registrationRequestDtoToUser(request);

        //Persist the user in the database
        userService.persistUser(appUser);

        //Generate a new confirmation token for thi user
        Confirmation confirmation = confirmationService.createNewConfirmationToken(appUser);

        //send confirmation email
        sendConfirmationEmail(appUser, confirmation.getToken());

        //return a dto with the information of the signed-up user
        return appUser;
    }

    private void checkUserUniqueness(String username, String email) {
        //Check if the email already exist
        boolean emailAlreadyExist = userService.existsUserByEmail(email);
        //Check if the username already exist
        boolean usernameAlreadyExist = userService.existsUserByUsername(username);

        //Create a list of violation
        Map<String, List<String>> errors = new HashMap<>();
        if (emailAlreadyExist) errors.put("email", List.of(ErrorMessagesService.userAlreadyExist()));
        if (usernameAlreadyExist) errors.put("username", List.of(ErrorMessagesService.userAlreadyExist()));

        //Throw an exception if violation occurs
        if (!errors.isEmpty()) throw new UserAlreadyExistsException(errors);
    }

    private void sendConfirmationEmail(AppUser appUser, UUID confirmationToken) {
        //send email to the user
        emailService.sendMimeTo(
                DeployDataService.registrationServiceEmail(),
                appUser.getEmail(),
                "Confirm to Complete registration process",
                new ConfirmationTemplate(
                        appUser.getFirstName() + " " + appUser.getLastName(),
                        "http://127.0.0.1:8080/registration/confirm?token=" + confirmationToken.toString()
                )
        );
    }
}
