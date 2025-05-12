package br.com.payment_integrator.unit.factory.dto;

import br.com.payment_integrator.domain.dto.invoice.response.FindInvoiceById.CustomerResponseDTO;
import br.com.payment_integrator.domain.dto.invoice.response.FindInvoiceById.InvoiceDetailsResponseDTO;
import br.com.payment_integrator.domain.dto.invoice.response.FindInvoiceById.MetadataResponseDTO;
import br.com.payment_integrator.domain.dto.invoice.response.FindInvoiceById.PixResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InvoiceDetailsResponseDTOMock {

    public static InvoiceDetailsResponseDTO createInvoiceDetailsResponseDTO() {
        return InvoiceDetailsResponseDTO.builder()
                .id("inv_001")
                .status("approved")
                .statusDetails("payment_completed")
                .amount(new BigDecimal("150.75"))
                .currency("BRL")
                .createdAt(LocalDateTime.now().minusDays(2))
                .approvedAt(LocalDateTime.now().minusDays(1))
                .paymentMethod("pix")
                .pix(new PixResponseDTO("1234567890", LocalDateTime.now().plusDays(1).toString()))
                .customer(new CustomerResponseDTO("cust_001", "maria.oliveira@example.com"))
                .metadata(new MetadataResponseDTO("order_987"))
                .build();
    }
}
