package com.github.terravivaproject.terraviva.email.services.templates;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Collections;

public class ConfirmationTemplate extends EmailTemplate {
    public ConfirmationTemplate(String firstName, String lastName, String link) {
        super.setAttachments(Collections.singletonMap("logo.png", "images/logo.png"));
        try {
            super.setBody(
                    new String(
                            new ClassPathResource("email-templates/confirmationTemplate.html")
                                    .getInputStream().readAllBytes()
                    ).replace("{**COMPLETE-NAME**}", firstName + " " + lastName)
                            .replace("{**LINK**}", link)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
