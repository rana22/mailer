package com.cofense.vision.mailer.controller;

import com.cofense.vision.mailer.service.MailerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mailer")
public class MailerController {

    private static final Logger log = LoggerFactory.getLogger(MailerController.class);

    @Autowired
    MailerService mailerService;

    @GetMapping
    public void sendMail(){
        log.info("sending email !!!!");
        mailerService.sendBulkEmails();

    }

}
