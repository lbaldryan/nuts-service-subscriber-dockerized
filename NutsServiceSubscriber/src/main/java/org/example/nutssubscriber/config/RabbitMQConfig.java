package org.example.nutssubscriber.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue nutsQueue() {
        return new Queue("nutsQueue", true); // durable = true
    }
}
