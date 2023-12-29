//package com.example.flightreservation.configuration;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//import java.util.Properties;
//
//@Configuration
//public class Mailconfig {
//
//    @Bean
//    public JavaMailSender javaMailSender(){
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smpt.gmail.com");
//        mailSender.setPort(587);
//        mailSender.setUsername("adkraj242@gmail.com");
//        mailSender.setPassword("");
//
//        Properties properties = new Properties();
//        properties.put("mail.smtp.auth","true");
//        properties.put("mail.smtp.starttls.enable",true);
//        mailSender.setJavaMailProperties(properties);
//        return mailSender;
//    }
//
//
//}
