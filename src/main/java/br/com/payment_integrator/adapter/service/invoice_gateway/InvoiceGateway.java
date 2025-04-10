package br.com.payment_integrator.adapter.service.invoice_gateway;

import br.com.payment_integrator.domain.entity.financial.Customer;
import br.com.payment_integrator.domain.entity.financial.Invoice;

public interface InvoiceGateway {

    void createInvoice(Invoice invoice, Customer customer);
}
