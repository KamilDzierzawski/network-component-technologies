package pl.edu.dik.userrabbitmq.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.edu.dik.userrabbitmq.model.AccountMessage;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMQCreateAccountPublisher implements CreateAccountPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.create.client.key}")
    private String createKey;

    @Override
    public void publish(AccountMessage account) {
        rabbitTemplate.convertAndSend(exchangeName, createKey, account);
        log.info("Published account {}", account);
    }
}
