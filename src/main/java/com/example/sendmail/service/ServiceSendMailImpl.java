package com.example.sendmail.service;

import com.example.sendmail.dto.HtmlMailRequest;
import com.example.sendmail.dto.HtmlMailRequestWithAttachment;
import com.example.sendmail.dto.SimpleMailRequest;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.util.ByteArrayDataSource;
import java.util.Base64;

@Service
public class ServiceSendMailImpl implements ServiceSendMail {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mail.sender}")
    private String senderEmail;

    @Override
    public SimpleMailRequest sendMail(SimpleMailRequest simpleMailRequest) {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(simpleMailRequest.getSendTo());
        mail.setSubject(simpleMailRequest.getSubject());
        mail.setText(simpleMailRequest.getMessage());
        mail.setFrom(senderEmail);

        javaMailSender.send(mail);
        return simpleMailRequest;
    }

    @SneakyThrows({MessagingException.class})
    @Override
    public HtmlMailRequest sendEmailWithHTML(HtmlMailRequest htmlMailRequest) {
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage());
        mimeMessageHelper.setFrom(senderEmail);
        mimeMessageHelper.setTo(htmlMailRequest.getSendTo());
        mimeMessageHelper.setSubject(htmlMailRequest.getSubject());
        mimeMessageHelper.setText(htmlMailRequest.getHtmlBody(), true);

        javaMailSender.send(mimeMessageHelper.getMimeMessage());
        return htmlMailRequest;
    }

    @SneakyThrows({MessagingException.class})
    @Override
    public HtmlMailRequestWithAttachment sendEmailWithHTMLAndAttachment(HtmlMailRequestWithAttachment htmlMailRequest) {
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(), true);
        mimeMessageHelper.setFrom(senderEmail);
        mimeMessageHelper.setTo(htmlMailRequest.getSendTo());
        mimeMessageHelper.setSubject(htmlMailRequest.getSubject());
        mimeMessageHelper.setText(htmlMailRequest.getHtmlBody(), true);

        DataSource dataSource = new ByteArrayDataSource(Base64.getDecoder().decode(htmlMailRequest.getBase64Attachment()), MediaType.IMAGE_JPEG_VALUE);

        System.out.println(mimeMessageHelper.isMultipart());

        mimeMessageHelper.addAttachment("my-image.jpg", new DataHandler(dataSource).getDataSource());

        javaMailSender.send(mimeMessageHelper.getMimeMessage());

        return htmlMailRequest;
    }

}
