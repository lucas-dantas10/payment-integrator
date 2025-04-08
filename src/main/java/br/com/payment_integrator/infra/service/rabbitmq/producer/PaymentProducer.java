package br.com.payment_integrator.infra.service.rabbitmq.producer;

import br.com.payment_integrator.config.RabbitMQConfig;
import br.com.payment_integrator.adapter.service.rabbitmq.producer.PaymentProducerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentProducer implements PaymentProducerGateway {

    private final RabbitTemplate rabbitTemplate;

    public void sendPaymentForProcessing(UUID paymentId) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.PAYMENT_CREATED_QUEUE, paymentId.toString());
    }
}
