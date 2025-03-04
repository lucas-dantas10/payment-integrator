package br.com.payment_integrator.domain.dto.payment.response;

import br.com.payment_integrator.domain.enums.PaymentMethodEnum;
import br.com.payment_integrator.domain.enums.StatusPaymentEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record PaymentResponseDTO(
    UUID id,
    BigDecimal amount,
    StatusPaymentEnum status,

    @JsonProperty("payment_method")
    PaymentMethodEnum paymentMethod
) {
}
