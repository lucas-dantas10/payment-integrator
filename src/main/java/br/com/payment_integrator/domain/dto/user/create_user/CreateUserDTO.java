package br.com.payment_integrator.domain.dto.user.create_user;

import jakarta.validation.constraints.NotNull;

public record CreateUserDTO(
        @NotNull(message = "O campo de name é obrigatório")
        String name,

        @NotNull(message = "O campo de email é obrigatório")
        String email,

        @NotNull(message = "O campo de password é obrigatório")
        String password
) {
}
