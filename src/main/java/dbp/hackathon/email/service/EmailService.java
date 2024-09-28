package dbp.hackathon.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import dbp.hackathon.QRapi.domain.QRapiService;
import dbp.hackathon.Ticket.Ticket;

@Service
public class EmailService {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    QRapiService qrapiService;

    @Async
    public void sendEmail(Ticket ticket) {

    }
}
