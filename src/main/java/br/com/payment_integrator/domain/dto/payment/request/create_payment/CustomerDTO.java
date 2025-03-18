package br.com.payment_integrator.domain.dto.payment.request.create_payment;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CustomerDTO(
        @NotNull(message = "O campo de name é obrigatório")
        @Min(value = 1, message = "O nome precisa ter pelo menos 3 caracteres")
        @Max(value = 200, message = "O nome espera no máximo 200 caracteres")
        String name,

        @NotNull(message = "O campo de email é obrigatório")
        @Max(value = 200, message = "O email espera no máximo 200 caracteres")
        String email
) {
}
