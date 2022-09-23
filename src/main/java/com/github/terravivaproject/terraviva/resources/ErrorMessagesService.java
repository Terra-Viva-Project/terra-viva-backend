package com.github.terravivaproject.terraviva.resources;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Load predefined messages from resources
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 09 09 2022
 */
@Service
@Accessors(fluent = true)
@PropertySource(value = "classpath:error_messages.properties")
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ErrorMessagesService {
    @Getter
    private static String confirmationTokenNotExist;
    @Getter
    private static String expirationPassed;
    @Getter
    private static String roleNotExist;
    @Getter
    private static String alreadyExist;
    @Getter
    private static String userAlreadyExist;
    @Getter
    private static String userDoesNotExist;
    @Getter
    private static String resourceNotExist;
    @Getter
    private static String requestError;
    @Getter
    private static String tooLate;
    @Getter
    private static String emailServiceError;
    @Getter
    private static String notEnoughAuthorization;
    @Getter
    private static String unauthorized;

    public ErrorMessagesService(
            @Value("${error.confirmationTokenNotExist}") String confirmationTokenNotExist,
            @Value("${error.expirationPassed}") String expirationPassed,
            @Value("${error.roleNotExist}") String roleNotExist,
            @Value("${error.alreadyExist}") String alreadyExist,
            @Value("${error.userAlreadyExist}") String userAlreadyExist,
            @Value("${error.userDoesNotExist}") String userDoesNotExist,
            @Value("${error.resourceNotExist}") String resourceNotExist,
            @Value("${error.requestError}") String requestError,
            @Value("${error.tooLate}") String tooLate,
            @Value("${error.emailServiceError}") String emailServiceError,
            @Value("${error.notEnoughAuthorization}") String notEnoughAuthorization,
            @Value("${error.unauthorized}") String unauthorized) {
        ErrorMessagesService.confirmationTokenNotExist = confirmationTokenNotExist;
        ErrorMessagesService.expirationPassed = expirationPassed;
        ErrorMessagesService.roleNotExist = roleNotExist;
        ErrorMessagesService.alreadyExist = alreadyExist;
        ErrorMessagesService.userDoesNotExist = userDoesNotExist;
        ErrorMessagesService.userAlreadyExist = userAlreadyExist;
        ErrorMessagesService.resourceNotExist = resourceNotExist;
        ErrorMessagesService.requestError = requestError;
        ErrorMessagesService.tooLate = tooLate;
        ErrorMessagesService.emailServiceError = emailServiceError;
        ErrorMessagesService.notEnoughAuthorization = notEnoughAuthorization;
        ErrorMessagesService.unauthorized = unauthorized;
    }
}
