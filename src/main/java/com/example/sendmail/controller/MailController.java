package com.example.sendmail.controller;


import com.example.sendmail.dto.HtmlMailRequest;
import com.example.sendmail.dto.HtmlMailRequestWithAttachment;
import com.example.sendmail.dto.SimpleMailRequest;
import com.example.sendmail.service.ServiceSendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private ServiceSendMail serviceSendMail;

    @PostMapping(value = "/")
    public SimpleMailRequest sendSimpleMail(@RequestBody SimpleMailRequest simpleMailRequest) {
        return serviceSendMail.sendMail(simpleMailRequest);
    }

    @PostMapping(value = "/with-html")
    public HtmlMailRequest sendHtmlMail(@RequestBody HtmlMailRequest htmlMailRequest) {
        return serviceSendMail.sendEmailWithHTML(htmlMailRequest);
    }

    @PostMapping("/with-html-and-attachment")
    public HtmlMailRequestWithAttachment sendHtmlMailWithAttachment(@RequestBody HtmlMailRequestWithAttachment htmlMailRequest) {
        return serviceSendMail.sendEmailWithHTMLAndAttachment(htmlMailRequest);
    }
}
