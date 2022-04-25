package com.example.ecommerce.service;

import com.example.ecommerce.exception.EmailSenderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    public void send(String to,String emailBody,String subject) {
        try {
            MimeMessage mimeMessage=mailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,"utf-8");
            helper.setText(emailBody);
            helper.setFrom("no-reply@bk.com");
            helper.setTo(to);
            helper.setSubject(subject);
            mailSender.send(mimeMessage);
        }
        catch (MessagingException e){
            throw new EmailSenderException("failed to send email");
        }



    }






}
