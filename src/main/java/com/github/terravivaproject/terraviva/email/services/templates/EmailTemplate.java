package com.github.terravivaproject.terraviva.email.services.templates;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public abstract class EmailTemplate {
    private Map<String, String> attachments;
    private String body;
}
