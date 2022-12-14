package com.github.terravivaproject.terraviva.configurations.security;

import dev.dmgiangi.budssecurity.configuration.BudsSecurityConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * desc
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 19 09 2022
 */
@Configuration
@Import(BudsSecurityConfiguration.class)
public class SecurityConfiguration {
}

