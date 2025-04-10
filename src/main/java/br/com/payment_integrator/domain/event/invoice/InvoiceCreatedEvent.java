package br.com.payment_integrator.domain.event.invoice;

import java.util.UUID;

public record InvoiceCreatedEvent(UUID invoiceId) {
}
