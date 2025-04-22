package br.com.payment_integrator.domain.dto.invoice.response.FindInvoiceById;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record InvoiceDetailsResponseDTO(
        String id,

        String status,

        @JsonProperty(value = "status_details")
        String statusDetails,

        BigDecimal amount,

        String currency,

        @JsonProperty(value = "created_at")
        LocalDateTime createdAt,

        @JsonProperty(value = "approved_at")
        LocalDateTime approvedAt,

        @JsonProperty(value = "payment_method")
        String paymentMethod,

        PixResponseDTO pix,

        CustomerResponseDTO customer,

        MetadataResponseDTO metadata
) {
}
