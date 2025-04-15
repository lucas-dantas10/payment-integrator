package br.com.payment_integrator.infra.dto.mercado_pago.response.invoice.create_invoice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateInvoiceResponseDTO(
        Integer id,

        String status,

        @JsonProperty(value = "status_detail")
        String statusDetail,

        @JsonProperty(value = "transaction_amount")
        BigDecimal transactionAmount,

        @JsonProperty(value = "currency_id")
        String currencyId,

        @JsonProperty(value = "date_created")
        String dateCreated,

        @JsonProperty(value = "date_approved")
        String dateApproved,

        @JsonProperty(value = "payment_method")
        PaymentMethodDTO paymentMethodDTO,

        @JsonProperty(value = "point_of_interaction")
        PointOfInteractionDTO pointOfInteractionDTO
) {

    public String toJson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
