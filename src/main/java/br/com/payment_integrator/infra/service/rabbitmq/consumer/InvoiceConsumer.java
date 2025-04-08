package br.com.payment_integrator.infra.service.rabbitmq.consumer;

import br.com.payment_integrator.config.RabbitMQConfig;
import br.com.payment_integrator.domain.service.invoice.ICreateInvoiceGatewayService;
import br.com.payment_integrator.adapter.service.rabbitmq.consumer.InvoiceConsumerGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class InvoiceConsumer implements InvoiceConsumerGateway {

    private final ICreateInvoiceGatewayService createInvoiceGatewayService;

    @Override
    @RabbitListener(queues = RabbitMQConfig.INVOICE_CREATED_QUEUE)
    public void createInvoice(String invoiceId) {
        log.info("Creating invoice: {}", invoiceId);

        createInvoiceGatewayService.createInvoice(invoiceId);
    }
}
