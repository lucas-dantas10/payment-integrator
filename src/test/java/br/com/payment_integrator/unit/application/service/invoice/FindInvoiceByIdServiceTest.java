package br.com.payment_integrator.unit.application.service.invoice;

import br.com.payment_integrator.application.service.invoice.FindInvoiceByIdService;
import br.com.payment_integrator.domain.builder.invoice.InvoiceResponseBuilder;
import br.com.payment_integrator.domain.dto.invoice.response.FindInvoiceById.InvoiceDetailsResponseDTO;
import br.com.payment_integrator.domain.entity.financial.Invoice;
import br.com.payment_integrator.domain.exception.invoice.InvoiceNotFoundException;
import br.com.payment_integrator.infra.repository.financial.InvoiceRepository;
import br.com.payment_integrator.unit.factory.dto.InvoiceDetailsResponseDTOMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindInvoiceByIdServiceTest {

    @InjectMocks
    private FindInvoiceByIdService findInvoiceByIdService;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private InvoiceResponseBuilder invoiceResponseBuilder;

    @Test
    public void shouldFindInvoiceById() {
        UUID invoiceId = UUID.randomUUID();
        Invoice invoice = new Invoice();
        InvoiceDetailsResponseDTO invoiceDetailsResponseDTO = InvoiceDetailsResponseDTOMock.createInvoiceDetailsResponseDTO();

        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
        when(invoiceResponseBuilder.build(invoice)).thenReturn(invoiceDetailsResponseDTO);

        InvoiceDetailsResponseDTO response = findInvoiceByIdService.execute(invoiceId.toString());

        assertEquals(invoiceDetailsResponseDTO, response);
        assertInstanceOf(InvoiceDetailsResponseDTO.class, response);
        verify(invoiceRepository, times(1)).findById(invoiceId);
        verify(invoiceResponseBuilder, times(1)).build(invoice);
    }

    @Test
    public void shouldThrowInvoiceNotFoundExceptionWhenNotFoundInvoice() {
        UUID invoiceId = UUID.randomUUID();

        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.empty());

        assertThrows(InvoiceNotFoundException.class, () -> findInvoiceByIdService.execute(invoiceId.toString()));
    }
}