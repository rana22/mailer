package com.cofense.vision.mailer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.AddressException;

@Service
public class MailerService {

    private static final Logger logger = LoggerFactory.getLogger(MailerService.class);

    @Autowired
    JavaMailSender javaMailSender;

    public void sendBulkEmails() {
        logger.info("Running bulk email sender !");
    }

    public boolean sendEmailAlert() {
        try {

            Address[] receivers = getReceiversAddress(retrieveFromFile());
            MimeMessage msg = javaMailSender.createMimeMessage();
            msg.setFrom("msg.service111@gmail.com");
            msg.setSubject("Subject-1-akk");
            msg.setText("");
            msg.setRecipients(Message.RecipientType.TO, receivers);
            javaMailSender.send(msg);
            return true;
        } catch (MessagingException ex) {
            logger.warn("Exception while sending email {} ", ex);
            return false;
        }
    }

    private Address[] getReceiversAddress(List<String> emailList) {
        List<Address> receivers = new ArrayList<>();
        for (String c : emailList) {

            try {
                InternetAddress emailId = new InternetAddress(c);
                emailId.validate();
                receivers.add(emailId);

            } catch (AddressException ex) {
                logger.warn("address exception {}", ex);
            }
        }
        return receivers.toArray(new Address[0]);
    }

    private List<String> retrieveFromFile(){
        List<String> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("var/emails.csv"));
            String line = "";
            //Read to skip the header
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] emailList = line.split(",");
                if (emailList.length > 0) {
                    //Save the employee details in Employee object
                    list.add(emailList[0]);
                }
            }

        }catch (IOException ex){
            logger.warn("Exception while retrieving emails from file",ex);
        }
        return list;
    }
}
