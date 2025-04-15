package br.com.payment_integrator.adapter.service.external_invoice_service;

import br.com.payment_integrator.domain.entity.financial.Customer;
import br.com.payment_integrator.domain.entity.financial.Invoice;
import br.com.payment_integrator.infra.dto.mercado_pago.response.invoice.create_invoice.CreateInvoiceResponseDTO;

public interface ExternalInvoiceService {

    CreateInvoiceResponseDTO createInvoice(Invoice invoice, Customer customer);
}
