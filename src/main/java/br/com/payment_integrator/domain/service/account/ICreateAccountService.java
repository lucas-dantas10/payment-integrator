package br.com.payment_integrator.domain.service.account;

import br.com.payment_integrator.domain.dto.account.create_account.CreateAccountDTO;
import br.com.payment_integrator.domain.entity.authentication.Account;

public interface ICreateAccountService {

    Account execute(CreateAccountDTO createAccountDTO) throws Exception;
}
