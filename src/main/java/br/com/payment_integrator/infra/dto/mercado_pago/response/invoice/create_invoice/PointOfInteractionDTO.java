package br.com.payment_integrator.infra.dto.mercado_pago.response.invoice.create_invoice;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PointOfInteractionDTO(

    @JsonProperty(value = "transaction_data")
    TransactionDataDTO transactionDataDTO
) {
}
