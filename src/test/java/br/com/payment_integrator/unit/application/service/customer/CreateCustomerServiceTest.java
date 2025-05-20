package br.com.payment_integrator.unit.application.service.customer;

import br.com.payment_integrator.application.service.customer.CreateCustomerService;
import br.com.payment_integrator.domain.dto.invoice.request.create_payment.CustomerDTO;
import br.com.payment_integrator.domain.entity.financial.Customer;
import br.com.payment_integrator.domain.entity.financial.Invoice;
import br.com.payment_integrator.infra.repository.financial.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateCustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CreateCustomerService createCustomerService;

    @Test
    public void shouldCreateCustomer() {
        CustomerDTO dto = new CustomerDTO("Teste", "teste@email.com");
        Invoice invoice = new Invoice();

        createCustomerService.execute(dto, invoice);

        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).save(captor.capture());

        Customer saved = captor.getValue();
        assertEquals("Teste", saved.getName());
        assertEquals("teste@email.com", saved.getEmail());
        assertEquals(invoice, saved.getInvoice());
        assertNotNull(saved.getCreatedAt());
    }
}