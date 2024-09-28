package dbp.hackathon.email.event;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import dbp.hackathon.email.service.EmailService;
import jakarta.mail.MessagingException;

public class EmailListener {

    @Autowired
    EmailService emailService;

    @Async
    @EventListener
    public void sendEmail(EmailEvent event) throws IOException, MessagingException {
        emailService.sendEmail(event.getTicket());
    }
}
