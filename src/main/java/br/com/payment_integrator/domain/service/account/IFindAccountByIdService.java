package br.com.payment_integrator.domain.service.account;

import br.com.payment_integrator.domain.entity.authentication.Account;
import br.com.payment_integrator.domain.exception.account.AccountNotFoundException;

public interface IFindAccountByIdService {

    Account execute(String userId) throws AccountNotFoundException;
}
