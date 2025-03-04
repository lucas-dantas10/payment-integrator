package br.com.payment_integrator.domain.dto.payment.create_payment;

import br.com.payment_integrator.domain.enums.PaymentMethodEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreatePaymentDTO(
        @JsonProperty("user_id")
        @NotNull(message = "O campo de user_id é obrigatório")
        String userId,

        @NotNull(message = "O campo de user_id é obrigatório")
        String currency,

        @NotNull(message = "O campo de amount é obrigatório")
        BigDecimal amount,

        @JsonProperty("payment_method")
        @NotNull(message = "O campo de payment_method é obrigatório")
        PaymentMethodEnum paymentMethod,

        @NotNull(message = "O campo de customer é obrigatório")
        CustomerDTO customer
) {
}
