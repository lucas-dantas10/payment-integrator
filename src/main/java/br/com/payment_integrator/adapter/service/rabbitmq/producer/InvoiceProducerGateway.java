package br.com.payment_integrator.adapter.service.rabbitmq.producer;

import java.util.UUID;

public interface InvoiceProducerGateway {

    void sendInvoiceForCreation(UUID invoiceId);
}
