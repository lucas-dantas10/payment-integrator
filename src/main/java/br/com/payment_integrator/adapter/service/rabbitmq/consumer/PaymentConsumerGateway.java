package br.com.payment_integrator.adapter.service.rabbitmq.consumer;

public interface PaymentConsumerGateway {

    void processPayment(String paymentId);
}
