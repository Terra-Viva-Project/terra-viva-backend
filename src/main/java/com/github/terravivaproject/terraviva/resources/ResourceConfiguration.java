package com.github.terravivaproject.terraviva.resources;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

/**
 * desc
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 23 09 2022
 */
@Configuration
@AllArgsConstructor
public class ResourceConfiguration {
    private final ErrorMessagesService errorMessagesService;
    private final DeployDataService deployDataService;
}
