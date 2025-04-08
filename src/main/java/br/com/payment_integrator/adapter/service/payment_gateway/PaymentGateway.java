package br.com.payment_integrator.adapter.service.payment_gateway;

import br.com.payment_integrator.domain.entity.financial.Customer;
import br.com.payment_integrator.domain.entity.financial.Payment;

public interface PaymentGateway {

    void createPayment(Payment payment, Customer customer);
}
