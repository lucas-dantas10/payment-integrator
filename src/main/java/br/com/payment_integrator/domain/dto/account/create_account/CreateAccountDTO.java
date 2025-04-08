package br.com.payment_integrator.domain.dto.account.create_account;

import jakarta.validation.constraints.NotNull;

public record CreateAccountDTO(
        @NotNull(message = "O campo de name é obrigatório")
        String name,

        @NotNull(message = "O campo de email é obrigatório")
        String email,

        @NotNull(message = "O campo de password é obrigatório")
        String password
) {
}
