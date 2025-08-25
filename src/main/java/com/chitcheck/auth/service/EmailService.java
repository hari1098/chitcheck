package com.chitcheck.auth.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOtpEmail(String to, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("your-email@gmail.com"); // Set your sending email
        message.setTo(to);
        message.setSubject("Your ChitCheck Verification Code");
        message.setText("Your OTP for account verification is: " + otp + ". It is valid for 5 minutes.");
        mailSender.send(message);
    }
}