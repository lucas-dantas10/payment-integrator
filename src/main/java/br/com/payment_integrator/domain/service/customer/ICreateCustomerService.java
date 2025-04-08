package br.com.payment_integrator.domain.service.customer;

import br.com.payment_integrator.domain.dto.payment.request.create_payment.CustomerDTO;
import br.com.payment_integrator.domain.entity.financial.Invoice;

public interface ICreateCustomerService {

    void createCustomer(CustomerDTO customerDTO, Invoice invoice);
}
