package pl.edu.dik.rabbitmq.listener;

import org.springframework.messaging.handler.annotation.Payload;
import pl.edu.dik.rabbitmq.model.AccountMessage;

public interface CreateAccountListener {
    void receiveUserCreatedEvent(@Payload AccountMessage event);
}
