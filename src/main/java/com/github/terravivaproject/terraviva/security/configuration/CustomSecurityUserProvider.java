package com.github.terravivaproject.terraviva.security.configuration;

import com.github.terravivaproject.terraviva.user.entities.enumerations.UserRole;
import com.github.terravivaproject.terraviva.user.repositories.UserRepository;
import dev.dmgiangi.budssecurity.entity.user.SecurityUser;
import dev.dmgiangi.budssecurity.entity.user.SecurityUserImpl;
import dev.dmgiangi.budssecurity.providers.SecurityUserProvider;
import lombok.AllArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * desc
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 22 09 2022
 */
@AllArgsConstructor
public class CustomSecurityUserProvider implements SecurityUserProvider {
    private final UserRepository userRepository;

    @Override
    public SecurityUser getUserByIdentifier(String identifier) {
        return userRepository.getByUsernameOrEmail(identifier)
                .map(u -> new SecurityUserImpl(
                                u.getId().toString(),
                                u.getPassword(),
                                u.getRole()
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
