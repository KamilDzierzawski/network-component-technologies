package pl.edu.dik.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:/rabbitmq.properties")
@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.create.client.key}")
    private String createKey;

    @Value("${rabbitmq.create.client.queue.name}")
    private String createQueueName;

    @Value("${rabbitmq.deadletter.exchange.name}")
    private String deadLetterExchangeName;

    @Value("${rabbitmq.deadletter.queue.name}")
    private String deadLetterQueueName;

    @Bean
    public Queue clientCreatedQueue() {
        return QueueBuilder.durable(createQueueName)
                .withArgument("x-dead-letter-exchange", deadLetterExchangeName)
                .build();
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding binding(Queue clientCreatedQueue, TopicExchange exchange) {
        return BindingBuilder.bind(clientCreatedQueue).to(exchange).with(createKey);
    }

    // Dead-letter exchange and queue
    @Bean
    public TopicExchange deadLetterExchange() {
        return new TopicExchange(deadLetterExchangeName);
    }

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder.durable(deadLetterQueueName).build();
    }

    @Bean
    public Binding deadLetterBinding(Queue deadLetterQueue, TopicExchange deadLetterExchange) {
        return BindingBuilder.bind(deadLetterQueue).to(deadLetterExchange).with("#");
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
