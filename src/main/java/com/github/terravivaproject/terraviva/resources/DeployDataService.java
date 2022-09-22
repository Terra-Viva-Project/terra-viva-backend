package com.github.terravivaproject.terraviva.resources;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * desc
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 09 09 2022
 */
@Service
@Accessors(fluent = true)
@PropertySource("classpath:config.properties")
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DeployDataService {
    @Getter
    private static String registrationServiceEmail;

    public DeployDataService(
            @Value("${registrationServiceEmail}") String registrationServiceEmail) {
        DeployDataService.registrationServiceEmail = registrationServiceEmail;
    }
}
