package br.com.payment_integrator.domain.dto.payment.create_payment;

import br.com.payment_integrator.domain.enums.PaymentMethodEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record CreatePaymentDTO(
        @JsonProperty("user_id")
        String userId,

        BigDecimal amount,

        @JsonProperty("payment_method")
        PaymentMethodEnum paymentMethod,

        CustomerDTO customer
) {
}
