package com.github.terravivaproject.terraviva.email.templates;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.Map;

/**
 * Abstract email template
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 10 09 2022
 */
@Getter
@Setter(value = AccessLevel.PROTECTED)
public abstract class EmailTemplate {
    private Map<String, File> attachments;
    private String body;
    private boolean isHtml;
}
