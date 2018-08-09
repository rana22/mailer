package com.cofense.vision.mailer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;

@Service
public class MailerService {

    private static final Logger logger = LoggerFactory.getLogger(MailerService.class);

    @Autowired
    JavaMailSender javaMailSender;
//    @Scheduled(initialDelay=6000,fixedDelay=5000)
    public void sendBulkEmails(){
        logger.info("Running bulk email sender !");

    }

    public boolean sendEmailAlert() {
        try {
            InternetAddress emails = new InternetAddress("ambar.rana123@gmail.com");
//            Address[] receivers = new Address[new InternetAddress("")];
            List<Address> receivers = new ArrayList<>();
            receivers.add(emails);
//            if (receivers.length == 0) {
//                return false;
//           }
            MimeMessage msg = javaMailSender.createMimeMessage();
            msg.setFrom("msg.service111@gmail.com");
            msg.setSubject("Subject-1");
            msg.setText( "" );
            msg.setRecipients(Message.RecipientType.TO, receivers.toArray(new Address[0]));
            javaMailSender.send(msg);
            return true;
        } catch (MessagingException ex) {
            logger.warn("Exception while sending email {} ", ex);
            return false;
        }
    }

}
