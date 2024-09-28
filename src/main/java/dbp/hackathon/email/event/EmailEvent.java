package dbp.hackathon.email.event;

import org.springframework.context.ApplicationEvent;

import dbp.hackathon.Ticket.Ticket;
import lombok.Getter;

@Getter
public class EmailEvent extends ApplicationEvent {
    private Ticket ticket;

    public EmailEvent(Object source, Ticket ticket) {
        super(source);
        this.ticket = ticket;
    }
}
