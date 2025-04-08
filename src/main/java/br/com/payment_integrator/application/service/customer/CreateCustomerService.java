package br.com.payment_integrator.application.service.customer;

import br.com.payment_integrator.domain.dto.invoice.request.create_payment.CustomerDTO;
import br.com.payment_integrator.domain.entity.financial.Customer;
import br.com.payment_integrator.domain.entity.financial.Invoice;
import br.com.payment_integrator.domain.service.customer.ICreateCustomerService;
import br.com.payment_integrator.infra.repository.financial.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateCustomerService implements ICreateCustomerService {
    private final CustomerRepository customerRepository;

    @Transactional
    public void createCustomer(CustomerDTO customerDTO, Invoice invoice) {
        Customer customer = Customer.builder()
                .name(customerDTO.name())
                .email(customerDTO.email())
                .invoice(invoice)
                .createdAt(LocalDateTime.now())
                .build();

        customerRepository.save(customer);
    }
}
