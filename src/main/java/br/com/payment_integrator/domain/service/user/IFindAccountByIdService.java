package br.com.payment_integrator.domain.service.user;

import br.com.payment_integrator.domain.entity.authentication.Account;
import br.com.payment_integrator.domain.exception.user.UserNotFoundException;

public interface IFindAccountByIdService {

    Account findAccountById(String userId) throws UserNotFoundException;
}
