package com.example.sendmail.service;/*
  Author DSR Sosnovsky 
  September 
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.InputStream;
import java.util.Properties;

@Service
public class ServiceSendMailImpl implements ServiceSendMail {

    @Autowired
    private JavaMailSender javaMailSenders;

    @Value("${mailsend.sendfrom}")
    private String sendfrom;

    @Value("${spring.mail.username}")
    private String userName;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.properties.mail.smtp.socketFactory.port}")
    private Integer port;

    @Value("${spring.mail.properties.mail.debug}")
    private String debug;

    @Value("${spring.mail.properties.mail.transport.protocol}")
    private String protocol;


    //spring.mail.properties.mail.smtp.starttls.required=true

    @Value("${spring.mail.properties.mail.smtp.socketFactory.class}")
    private String sslClass;

    @Value("${spring.mail.properties.mail.smtp.socketFactory.fallback}")
    private String fallback;

    @Value("${spring.mail.properties.mail.smtp.ssl.enable}")
    private String useSsl;

    @Override
    public String sendMail(String sendTo, String subject, String message) {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(sendTo);
        mail.setSubject(subject);
        mail.setText(message);
        mail.setFrom(sendfrom);

        try {
            javaMailSenders.send(mail);
            return "Mail was sended to :" + sendTo;
        } catch (MailException e) {
            return "Mail was unsended " + e.getMessage();
        }

    }

    @Override
    public String sendCustomMail(String sendTo, String subject, String message) {
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);

        properties.put("mail.debug", debug);
        properties.put("mail.transport.protocol", protocol);
        properties.put("mail.smtp.socketFactory.class", sslClass);
        properties.put("mail.smtp.socketFactory.fallback", fallback);
        properties.put("mail.smtp.ssl.enable", useSsl);
        Session session = Session.getInstance(properties, auth);
        Message msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(userName.toString()));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendTo));
            msg.setSubject(subject);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        MimeMultipart multipart = new MimeMultipart();
        BodyPart messageBodyPart = new MimeBodyPart();
        String htmlText = "<H1>Hello From Java Mail Sender!\n" + message + "\n</H1><img src=\"cid:image\">"+"\nbest regards, BestCaffe.Club";
        try {
            messageBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(messageBodyPart);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // second part (the image)
        messageBodyPart = new MimeBodyPart();
        DataSource fds = new FileDataSource("C:/logo.png");

        try {
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image>");
            multipart.addBodyPart(messageBodyPart);
            msg.setContent(multipart);

            Transport.send(msg);
            return "Mail was sended to :" + sendTo;
        } catch (MessagingException e) {
            return "Mail was unsended " + e.getMessage();
        }
    }
}
