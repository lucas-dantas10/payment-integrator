package br.com.payment_integrator.domain.dto.payment.request.create_payment;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductDTO(
        @NotNull(message = "O campo de nome é obrigatório")
        @Size(min = 3, max = 200, message = "O nome precisa ter entre 3 e 200 caracteres")
        String name,

        @Size(min = 3, max = 200, message = "A descrição precisa ter entre 3 e 200 caracteres")
        String description,

        @NotNull(message = "O campo de price é obrigatório")
        @Min(value = 1, message = "O preço mínimo é 1")
        BigDecimal price,

        @NotNull(message = "O campo de quantity é obrigatório")
        @Min(value = 1, message = "A quantidade mínima é 1")
        Integer quantity
) {
}
