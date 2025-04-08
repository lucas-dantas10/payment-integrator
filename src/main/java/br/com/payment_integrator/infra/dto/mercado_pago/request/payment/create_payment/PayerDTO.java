package br.com.payment_integrator.infra.dto.mercado_pago.request.payment.create_payment;

import lombok.Builder;

@Builder
public record PayerDTO(
        String email
) {
}
