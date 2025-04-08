package br.com.payment_integrator.domain.service.user;

import br.com.payment_integrator.domain.dto.account.create_account.CreateAccountDTO;
import br.com.payment_integrator.domain.entity.authentication.Account;

public interface ICreateAccountService {

    Account createAccount(CreateAccountDTO createAccountDTO) throws Exception;
}
