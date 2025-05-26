package pl.edu.dik.userrabbitmqadapters.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import pl.edu.dik.userrabbitmqadapters.model.AccountEnt;

@Repository
@RequiredArgsConstructor
public class RabbitMQCreateAccountPublisher implements CreateAccountPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.create.client.key}")
    private String createKey;

    @Override
    public void publish(AccountEnt account) {
        rabbitTemplate.convertAndSend(exchangeName, createKey, account);
    }
}
