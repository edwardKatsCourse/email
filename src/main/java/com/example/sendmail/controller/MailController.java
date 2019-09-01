package com.example.sendmail.controller;/*
  Author DSR Sosnovsky 
  September 
*/

import com.example.sendmail.service.ServiceSendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MailController {

    @Autowired
    private ServiceSendMail serviceSendMail;

    @GetMapping(value = "/mail", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMail() {
        return serviceSendMail.sendMail("null", "test hello from java spring", "Testik");
    }

    @GetMapping(value = "/mail2", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMailCustom() {
        return serviceSendMail.sendCustomMail("cool-team@bestcaffe.club", "test hello from java spring", "Testik");
    }

}
