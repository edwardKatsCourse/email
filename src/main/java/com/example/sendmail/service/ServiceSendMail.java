package com.example.sendmail.service;

import com.example.sendmail.dto.HtmlMailRequest;
import com.example.sendmail.dto.HtmlMailRequestWithAttachment;
import com.example.sendmail.dto.SimpleMailRequest;

public interface ServiceSendMail {
    SimpleMailRequest sendMail(SimpleMailRequest simpleMailRequest);
    HtmlMailRequest sendEmailWithHTML(HtmlMailRequest htmlMailRequest);
    HtmlMailRequestWithAttachment sendEmailWithHTMLAndAttachment(HtmlMailRequestWithAttachment htmlMailRequest);
}
