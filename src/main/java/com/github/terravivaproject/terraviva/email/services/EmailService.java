package com.github.terravivaproject.terraviva.email.services;

import com.github.terravivaproject.terraviva.email.templates.EmailTemplate;
import com.github.terravivaproject.terraviva.exceptions.EmailServiceException;
import com.github.terravivaproject.terraviva.resources.ErrorMessagesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * A service to send email
 *
 * @author Gianluigi De Marco
 * @version 0.1
 * @since 10 09 2022
 */
@Service
@Async
@Slf4j
@AllArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public <E extends EmailTemplate> void sendMimeTo(String emailFrom, String emailTo, String subject, E template) {
        //Create an empty email
        MimeMessage email = mailSender.createMimeMessage();
        try {
            //Create a mail builder helper
            MimeMessageHelper emailBuilder = new MimeMessageHelper(email, true);

            emailBuilder.setFrom(emailFrom);
            emailBuilder.setTo(emailTo);
            emailBuilder.setSubject(subject);

            //Set the body of the email from the template
            emailBuilder.setText(template.getBody(), template.isHtml());

            //add to emailBuilder every attachment in the template
            template.getAttachments().forEach(
                    (name, file) -> {
                        try {
                            emailBuilder.addAttachment(name, file);
                        } catch (MessagingException e) {
                            log.error("FATAL -> EmailService: Error on attachment insert", e);
                            throw new EmailServiceException(ErrorMessagesService.emailServiceError());
                        }
                    }
            );

            //Send the email
            mailSender.send(email);

        } catch (MessagingException e) {
            log.error("FATAL -> EmailService: Error on Mime Message Creation", e);
            throw new EmailServiceException(ErrorMessagesService.emailServiceError());
        } catch (MailException e) {
            log.error("FATAL -> EmailService: Error on mail sending", e);
            throw new EmailServiceException(ErrorMessagesService.emailServiceError());
        }
    }
}
