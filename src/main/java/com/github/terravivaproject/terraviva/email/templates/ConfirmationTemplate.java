package com.github.terravivaproject.terraviva.email.templates;

import com.github.terravivaproject.terraviva.exceptions.EmailServiceException;
import com.github.terravivaproject.terraviva.resources.ErrorMessagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Concrete email template for registration confirmation email
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 10 09 2022
 */
@Slf4j
public class ConfirmationTemplate extends EmailTemplate {
    private static File logoImage = null;
    private static File htmlBody = null;

    /**
     * Constructor for ConfirmationTemplate.
     *
     * @param fullName a {@link java.lang.String} object
     * @param link     a {@link java.lang.String} object
     */
    public ConfirmationTemplate(String fullName, String link) {
        //Load resource file
        ConfirmationTemplate.loadResources();

        //create a variable for the body of the email
        String body;

        body = adaptMessage(fullName, link);

        //set the field in the parent
        this.setHtml(true);
        this.setAttachments(Collections.singletonMap("logo.png", logoImage));
        this.setBody(body);
    }

    private static String adaptMessage(String fullName, String link) {
        String body;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(htmlBody))) {
            //With a stream read all lines of the file and replace placeholder with value
            body = bufferedReader.
                    lines().map(line -> {
                                line = line.replace("{**COMPLETE-NAME**}", fullName);
                                line = line.replace("{**LINK**}", link);
                                return line;
                            }
                    ).collect(Collectors.joining("\n"));
        } catch (IOException e) {
            log.error("FATAL -> EmailService: Error on File Stream of Confirmation email template ", e);
            throw new EmailServiceException(ErrorMessagesService.emailServiceError());
        }

        return body;
    }

    private static void loadResources() {
        if (logoImage == null || htmlBody == null) {
            try {
                logoImage = ResourceUtils.getFile("classpath:images/logo.png");
                htmlBody = ResourceUtils.getFile("classpath:email-templates/confirmationTemplate.html");
            } catch (FileNotFoundException e) {
                log.error("FATAL -> EmailService: Confirmation email template not Found", e);
                throw new EmailServiceException(ErrorMessagesService.emailServiceError());
            }
        }
    }
}
