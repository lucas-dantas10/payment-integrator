package br.com.payment_integrator.application.service.customer;

import br.com.payment_integrator.domain.dto.payment.request.create_payment.CustomerDTO;
import br.com.payment_integrator.domain.entity.financial.Customer;
import br.com.payment_integrator.domain.entity.financial.Payment;
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
    public void createCustomer(CustomerDTO customerDTO, Payment payment) {
        Customer customer = Customer.builder()
                .name(customerDTO.name())
                .email(customerDTO.email())
                .payment(payment)
                .createdAt(LocalDateTime.now())
                .build();

        customerRepository.save(customer);
    }
}
