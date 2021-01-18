package com.chinabox.delivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Properties;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("raspbian29@gmail.com");
        mailSender.setPassword("ipgwfntppclblczc");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    public void sendSimpleMessage(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jora@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);

    }


    public void sendSimpleMessage(SimpleMailMessage origin) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(Objects.requireNonNull(origin.getFrom()));
        message.setTo(Objects.requireNonNull(origin.getTo()));
        message.setSubject(Objects.requireNonNull(origin.getSubject()));
        message.setText(Objects.requireNonNull(origin.getText()));
        emailSender.send(message);

    }
}

