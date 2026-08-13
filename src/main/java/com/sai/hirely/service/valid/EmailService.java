package com.sai.hirely.service.valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class EmailService implements NotificationService{
//    void sendVerificationEmail(String to, String verificationToken);
//    void sendPasswordResetEmail(String to, String resetToken);
    private final JavaMailSender sender;

    @Autowired
    public EmailService(JavaMailSender sender) {
        this.sender = sender;
    }
    @Async("emailExecutor")
    public void sendAsync(String... args) {
        assert args.length>3 : "Only 3 arguments accepted";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(args[0]);
        message.setSubject(args[1]);
        message.setText(args[2]);
        sender.send(message);
    }

    @Override
    public void send(String... args) {
        assert args.length>3 : "Only 3 arguments accepted";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(args[0]);
        message.setSubject(args[1]);
        message.setText(args[2]);
        sender.send(message);
    }
}
