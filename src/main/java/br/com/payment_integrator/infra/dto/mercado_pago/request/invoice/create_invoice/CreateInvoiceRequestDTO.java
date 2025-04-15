package br.com.payment_integrator.infra.dto.mercado_pago.request.invoice.create_invoice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CreateInvoiceRequestDTO(

        @JsonProperty(value = "transaction_amount")
        BigDecimal transactionAmount,

        @JsonProperty(value = "payment_method_id")
        String paymentMethodId,

        PayerDTO payer
) {
}
