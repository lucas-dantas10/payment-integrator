package br.com.payment_integrator.gateway.service.rabbitmq.producer;

import java.util.UUID;

public interface PaymentProducerGateway {

    void sendPaymentForProcessing(UUID paymentId);
}
