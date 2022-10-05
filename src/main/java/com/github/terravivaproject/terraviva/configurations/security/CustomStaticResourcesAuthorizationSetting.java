package com.github.terravivaproject.terraviva.configurations.security;

import dev.dmgiangi.budssecurity.authorizations.StaticResourcesAuthorizationSetting;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * class
 *
 * @author Gianluigi De Marco
 * @version x
 * @since 03 10 2022
 */
@Component
public class CustomStaticResourcesAuthorizationSetting implements StaticResourcesAuthorizationSetting {
    @Override
    public List<String> getPublicResourcesPath() {
        return List.of(
                "/swagger-ui.*"
        );
    }

    @Override
    public Map<String, List<String>> getProtectedResourcesNeededAuthorization() {
        return null;
    }
}