package pl.edu.dik.userrabbitmqadapters.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Repository;
import pl.edu.dik.userrabbitmqadapters.model.AccountEnt;

@Repository
@RequiredArgsConstructor
public class RabbitMQCreateAccountPublisher implements CreateAccountPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publish(AccountEnt account) {

        rabbitTemplate.convertAndSend(account);
    }
}
