package br.com.payment_integrator.domain.service.account;

import br.com.payment_integrator.domain.entity.authentication.Account;

public interface IFindAccountByApiKeyService {

    public Account execute(final String apiKey);
}
