package com.github.terravivaproject.terraviva.configurations.security;

import com.github.terravivaproject.terraviva.user.entities.enumerations.UserRole;
import com.github.terravivaproject.terraviva.user.repositories.UserRepository;
import dev.dmgiangi.budssecurity.authentication.provider.SecurityUserProvider;
import dev.dmgiangi.budssecurity.securitycontext.SecurityUser;
import dev.dmgiangi.budssecurity.securitycontext.UuidSecurityUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * desc
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 22 09 2022
 */

@Service
@AllArgsConstructor
public class CustomSecurityUserProvider implements SecurityUserProvider {
    private final UserRepository userRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public SecurityUser findUserByIdentifier(String identifier) {
        return userRepository.getByUsernameOrEmail(identifier)
                .map(u -> new UuidSecurityUser(
                                u.getId(),
                                u.getPassword(),
                                u.getRoles()
                                        .stream()
                                        .map(UserRole::toString)
                                        .collect(Collectors.toUnmodifiableSet()),
                                Set.of(u.getId().toString(), u.getUsername(), u.getEmail()),
                                true,
                                !u.getLocked(),
                                true,
                                u.getVerified()
                        )
                )
                .orElse(null);
    }
}
