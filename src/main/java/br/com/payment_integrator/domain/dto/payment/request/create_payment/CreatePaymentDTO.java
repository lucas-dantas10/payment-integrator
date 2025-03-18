package br.com.payment_integrator.domain.dto.payment.request.create_payment;

import br.com.payment_integrator.domain.enums.PaymentMethodEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record CreatePaymentDTO(
        @JsonProperty("user_id")
        @NotNull(message = "O campo de user_id é obrigatório")
        String userId,

        @NotNull(message = "O campo de user_id é obrigatório") // TODO: Validar se tem como fixar os valores necessarios nesse campo
        String currency,

        @JsonProperty("total_amount")
        @NotNull(message = "O campo de total_amount é obrigatório")
        @Min(value = 1, message = "O valor total mínimo é 1") // TODO: Validar se vai ser por centavos ou real
        BigDecimal totalAmount,

        @JsonProperty("payment_method")
        @NotNull(message = "O campo de payment_method é obrigatório")
        PaymentMethodEnum paymentMethod,

        @Valid
        @NotNull(message = "O campo de customer é obrigatório")
        CustomerDTO customer,

        @Valid
        @NotNull(message = "O campo de products é obrigatório")
        List<ProductDTO> products
) {
}
