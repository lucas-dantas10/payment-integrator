package br.com.payment_integrator.domain.dto.invoice.response.FindInvoiceById;

import lombok.Builder;

@Builder
public record CustomerResponseDTO(
        String name,
        String email
) {
}
