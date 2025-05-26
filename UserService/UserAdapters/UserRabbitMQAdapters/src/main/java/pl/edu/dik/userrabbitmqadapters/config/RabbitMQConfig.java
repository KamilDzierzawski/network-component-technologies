package pl.edu.dik.userrabbitmqadapters.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.create.client.key}")
    private String createKey;

    @Value("${rabbitmq.activate.client.key}")
    private String activateKey;
}
