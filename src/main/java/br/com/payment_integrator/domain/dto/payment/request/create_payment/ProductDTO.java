package br.com.payment_integrator.domain.dto.payment.request.create_payment;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductDTO(
        @NotNull(message = "O campo de nome é obrigatório")
        @Min(value = 1, message = "O nome precisa ter pelo menos 3 caracteres")
        String name,

        @Min(value = 1, message = "A descrição precisa ter pelo menos 3 caracteres")
        @Max(value = 300, message = "A descrição espera no máximo 300 caracteres")
        String description,

        @NotNull(message = "O campo de price é obrigatório")
        @Min(value = 1, message = "O preço mínimo é 1") // TODO: Validar se vai ser por centavos ou real
        BigDecimal price,

        @NotNull(message = "O campo de quantity é obrigatório")
        @Min(value = 1, message = "A quantidade mínima é 1")
        Integer quantity
) {
}
