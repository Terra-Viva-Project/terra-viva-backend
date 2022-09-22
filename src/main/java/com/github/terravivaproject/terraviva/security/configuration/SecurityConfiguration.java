package com.github.terravivaproject.terraviva.security.configuration;

import com.github.terravivaproject.terraviva.user.repositories.UserRepository;
import dev.dmgiangi.budssecurity.BudsSecurityConfiguration;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
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
@AllArgsConstructor
public class SecurityConfiguration {
    private final UserRepository userRepository;

    @Bean
    public CustomSecurityUserProvider customSecurityUserProvider() {
        return new CustomSecurityUserProvider(userRepository);
    }

}

