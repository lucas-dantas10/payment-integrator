package br.com.payment_integrator.infra.service.rabbitmq.consumer;

import br.com.payment_integrator.config.RabbitMQConfig;
import br.com.payment_integrator.domain.service.invoice.IProcessPaymentService;
import br.com.payment_integrator.adapter.service.rabbitmq.consumer.PaymentConsumerGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentConsumer implements PaymentConsumerGateway {

    private final IProcessPaymentService processPaymentService;

    @Override
    @RabbitListener(queues = RabbitMQConfig.PAYMENT_CREATED_QUEUE)
    public void processPayment(String paymentId) {
        log.info("Processing payment: {}", paymentId);

        processPaymentService.processPayment(paymentId);
    }
}
