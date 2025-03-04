package br.com.payment_integrator.config;

import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String PAYMENT_CREATED_QUEUE = "payment-created";

    @Bean
    public Declarable paymentProcessorQueue() {
        return new Queue(PAYMENT_CREATED_QUEUE);
    }
}
