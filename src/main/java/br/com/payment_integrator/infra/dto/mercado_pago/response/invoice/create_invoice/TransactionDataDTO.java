package br.com.payment_integrator.infra.dto.mercado_pago.response.invoice.create_invoice;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TransactionDataDTO(

        @JsonProperty(value = "qr_code")
        String qrCode,

        @JsonProperty(value = "ticket_url")
        String ticketUrl
) {
}
