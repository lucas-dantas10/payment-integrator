package br.com.payment_integrator.domain.dto.invoice.response.FindInvoiceById;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record MetadataResponseDTO(

        @JsonProperty(value = "external_payment_id")
        String externalPaymentId
) {
}
