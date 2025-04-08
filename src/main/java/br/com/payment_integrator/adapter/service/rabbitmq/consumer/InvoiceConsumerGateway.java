package br.com.payment_integrator.adapter.service.rabbitmq.consumer;

public interface InvoiceConsumerGateway {

    void createInvoice(String invoiceId);
}
