package br.com.payment_integrator.application.listener.invoice;

import br.com.payment_integrator.adapter.service.rabbitmq.producer.InvoiceProducerGateway;
import br.com.payment_integrator.domain.event.invoice.InvoiceCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class InvoiceEventListener {

    private final InvoiceProducerGateway invoiceProducerGateway;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleInvoiceCreated(InvoiceCreatedEvent event) {
        log.info("Enviando fatura para processamento: {}", event.invoiceId());
        invoiceProducerGateway.sendInvoiceForCreation(event.invoiceId());
    }
}
