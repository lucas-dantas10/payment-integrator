package br.com.payment_integrator.infra.service.rabbitmq.producer;

import br.com.payment_integrator.config.RabbitMQConfig;
import br.com.payment_integrator.adapter.service.rabbitmq.producer.InvoiceProducerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceProducer implements InvoiceProducerGateway {

    private final RabbitTemplate rabbitTemplate;

    public void sendInvoiceForCreation(UUID invoiceId) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.INVOICE_CREATED_QUEUE, invoiceId.toString());
    }
}
