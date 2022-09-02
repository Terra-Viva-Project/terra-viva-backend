package com.github.terravivaproject.terraviva.email.services;

import com.github.terravivaproject.terraviva.email.services.templates.EmailTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
@Async
public class EmailService {
    private final static Logger logger = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String mailFrom;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public <E extends EmailTemplate> void sendMimeTo(String email, String title, E template) {
        logger.info("Start sending email to: " + email);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(mailFrom);
            helper.setTo(email);
            helper.setSubject(title);
            helper.setText(template.getBody(), true);
            template.getAttachments().forEach(
                    (name, path) -> {
                        try {
                            helper.addAttachment(
                                    name,
                                    new ClassPathResource(path).getFile()
                            );
                        } catch (MessagingException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            logger.error("Cannot create Mime message.", e);
        }
        logger.info("Email successfully sent to: " + email);
    }
}
