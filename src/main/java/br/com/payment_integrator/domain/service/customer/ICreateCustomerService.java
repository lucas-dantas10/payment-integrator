package br.com.payment_integrator.domain.service.customer;

import br.com.payment_integrator.domain.dto.invoice.request.create_payment.CustomerDTO;
import br.com.payment_integrator.domain.entity.financial.Invoice;

public interface ICreateCustomerService {

    void execute(CustomerDTO customerDTO, Invoice invoice);
}
