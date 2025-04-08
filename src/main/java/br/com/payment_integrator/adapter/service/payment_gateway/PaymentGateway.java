package br.com.payment_integrator.adapter.service.payment_gateway;

import br.com.payment_integrator.domain.entity.financial.Customer;
import br.com.payment_integrator.domain.entity.financial.Invoice;

public interface PaymentGateway {

    void createPayment(Invoice invoice, Customer customer);
}
