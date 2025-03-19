package br.com.payment_integrator.domain.service.customer;

import br.com.payment_integrator.domain.dto.payment.request.create_payment.CustomerDTO;
import br.com.payment_integrator.domain.entity.financial.Payment;

public interface ICreateCustomerService {

    void createCustomer(CustomerDTO customerDTO, Payment payment);
}
