package com.metrodata.serverapp.service.impl;

import com.metrodata.serverapp.exception.CustomException;
import com.metrodata.serverapp.model.request.Email;
import com.metrodata.serverapp.service.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
@Slf4j
@AllArgsConstructor
public class EmailSenderService implements EmailService {

    private JavaMailSender mailSender;

    @Override
    public Email sendSimpleEmail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());
        mailSender.send(message);
        log.info("Successfully send email to {}", email.getTo());
        return email;
    }

    @Override
    public Email sendEmailWithAttachment(Email email) {
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getBody());
            FileSystemResource fileSystemResource = new FileSystemResource(new File(email.getAttachment()));
            helper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);

            mailSender.send(mimeMessage);
            log.info("Successfully send email with Attachment to {}", email.getTo());

        }catch (MessagingException e){
            log.error("Failed to send email with Attachment to : {}", email.getTo());
            throw new CustomException(e.getMessage(),"FAILED_TO_SEND_EMAIL", 500);
        }
        return email;
    }
}
