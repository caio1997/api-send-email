package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Properties;

@RestController
public class FeedbackController {

    @Autowired
    private EmailCfg emailCfg;

    @GetMapping("/sucesso")
    public ModelAndView sucesso(){
        return new ModelAndView("sucesso");
    }

    @GetMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @PostMapping("/email")
    public ModelAndView sendFeedback(Feedback feedback) {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setUsername(emailCfg.getUsername());
        mailSender.setPassword(emailCfg.getPassword());

        Properties mailProperties = new Properties();

        mailProperties.put("mail.smtp.host", emailCfg.getHost());
        mailProperties.put("mail.smtp.port", emailCfg.getPort());
        mailProperties.put("mail.smtp.starttls.enable", "true");
        mailProperties.put("mail.smtp.socketFactory.port", emailCfg.getPort());
        mailProperties.put("mail.smtp.socketFactory.fallback", "false");
        mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        mailSender.setJavaMailProperties(mailProperties);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(feedback.getEmail());
        mailMessage.setSubject(feedback.getTitle());
        mailMessage.setText(feedback.getText());

        mailSender.send(mailMessage);

        return new ModelAndView("sucesso");
    }
}
