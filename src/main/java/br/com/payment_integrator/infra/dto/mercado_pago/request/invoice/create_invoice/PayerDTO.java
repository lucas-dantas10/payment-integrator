package br.com.payment_integrator.infra.dto.mercado_pago.request.invoice.create_invoice;

import lombok.Builder;

@Builder
public record PayerDTO(
        String email
) {
}
