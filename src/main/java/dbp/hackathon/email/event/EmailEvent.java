package dbp.hackathon.email.event;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class EmailEvent extends ApplicationEvent {

    public EmailEvent(Object source) {
        super(source);

    }
}
