package br.com.payment_integrator.gateway.service.rabbitmq.consumer;

public interface PaymentConsumerGateway {

    void processPayment(String paymentId);
}
