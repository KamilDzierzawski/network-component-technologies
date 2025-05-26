package pl.edu.dik.rabbitmq.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import pl.edu.dik.domain.model.account.Account;
import pl.edu.dik.ports._interface.AccountService;
import pl.edu.dik.rabbitmq.model.AccountMessage;

@Component
@Slf4j
@RequiredArgsConstructor
public class RabbitMQCreateAccountListener implements CreateAccountListener {

    private final ModelMapper modelMapper;
    private final AccountService accountService;

    @RabbitListener(queues = "${rabbitmq.create.client.queue.name}")
    public void receiveUserCreatedEvent(@Payload AccountMessage event) {
        try {
            log.info("Received event {}", event);
            accountService.createAccount(modelMapper.map(event, Account.class));
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException("Failed to process event", e);
        }
    }
}
