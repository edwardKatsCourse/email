package com.example.sendmail.service;/*
  Author DSR Sosnovsky 
  September 
*/

public interface ServiceSendMail {
    String sendMail(String sendTo, String subject, String message);
    String sendCustomMail(String sendTo, String subject, String message);
}
