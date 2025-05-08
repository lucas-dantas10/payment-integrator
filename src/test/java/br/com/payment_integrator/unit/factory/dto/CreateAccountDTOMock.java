package br.com.payment_integrator.unit.factory.dto;

import br.com.payment_integrator.domain.dto.account.create_account.CreateAccountDTO;

public class CreateAccountDTOMock {

    public static CreateAccountDTO createAccountDTO() {
        return new CreateAccountDTO("Teste", "teste@email.com");
    }

    public static CreateAccountDTO createAccountDTO(String name, String email) {
        return new CreateAccountDTO(name, email);
    }
}
