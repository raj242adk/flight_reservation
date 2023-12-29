package com.example.flightreservation.service.others;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to,String subject,String body){
        SimpleMailMessage mailMessage= new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        mailSender.send(mailMessage);
    }
    public void sendEmailWithTemplate(String to, String subject, String template, Map<String, Object> variables) {
        String body = processTemplate(template, variables);
        sendEmail(to, subject, body);
    }

    private String processTemplate(String template, Map<String, Object> variables) {
        for (Map.Entry<String, Object> entry : variables.entrySet()) {
            String variable = "${" + entry.getKey() + "}";
            template = template.replace(variable, entry.getValue().toString());
        }
        return template;
    }
}
