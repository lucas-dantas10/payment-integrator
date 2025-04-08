package br.com.payment_integrator.domain.dto.invoice.request.create_payment;

import jakarta.validation.constraints.*;

public record CustomerDTO(
        @NotNull(message = "O campo de name é obrigatório")
        @Size(min = 3, max = 200, message = "O nome precisa ter entre 3 e 200 caracteres")
        String name,

        @NotNull(message = "O campo de email é obrigatório")
        @Size(min = 3, max = 200, message = "O email precisa ter entre 3 e 200 caracteres")
        @Email(message = "O email precisa ser válido")
        String email
) {
}
