package br.com.payment_integrator.domain.dto.invoice.response.FindInvoiceById;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record PixResponseDTO(

        @JsonProperty(value = "qr_code")
        String qrCode,

        @JsonProperty(value = "payment_link")
        String paymentLink
) {
}
