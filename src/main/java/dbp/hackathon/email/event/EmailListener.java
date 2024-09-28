package dbp.hackathon.email.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import dbp.hackathon.email.service.EmailService;

public class EmailListener {

    @Autowired
    EmailService emailService;

    @Async
    @EventListener
    public void sendEmail(EmailEvent event) {
        emailService.sendEmail(event.getTicket());
    }
}
