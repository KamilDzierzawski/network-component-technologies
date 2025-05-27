package pl.edu.dik.userrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = "classpath:/rabbitmq.properties")
@Configuration
public class RabbitMQConfig {


    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.create.client.key}")
    private String createKey;

    @Value("${rabbitmq.create.client.queue.name}")
    private String createQueueName;

    @Value("${rabbitmq.delete.client.key}")
    private String deleteKey;

    @Value("${rabbitmq.delete.client.queue.name}")
    private String deleteQueueName;

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Queue clientCreateQueue() {
        return QueueBuilder.durable(createQueueName).build();
    }

    @Bean
    public Queue clientDeleteQueue() {
        return QueueBuilder.durable(deleteQueueName).build();
    }

    @Bean
    public Binding clientCreateBinding() {
        return BindingBuilder.bind(clientCreateQueue()).to(exchange()).with(createKey);
    }

    @Bean
    public Binding clientDeleteBinding() {
        return BindingBuilder.bind(clientDeleteQueue()).to(exchange()).with(deleteKey);
    }

    @Bean
    public MessageRecoverer messageRecoverer(RabbitTemplate rabbitTemplate) {
        return new RepublishMessageRecoverer(rabbitTemplate, exchangeName, deleteKey);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
