package br.com.payment_integrator.application.service.account;

import br.com.payment_integrator.domain.entity.authentication.Account;
import br.com.payment_integrator.domain.exception.account.AccountNotFoundException;
import br.com.payment_integrator.domain.service.account.IFindAccountByApiKeyService;
import br.com.payment_integrator.infra.repository.authentication.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class FindAccountByApiKeyService implements IFindAccountByApiKeyService {

    private final AccountRepository accountRepository;

    public Account execute(final String apiKey) {
        return accountRepository
                .findByApiKey(apiKey)
                .orElseThrow(AccountNotFoundException::new);
    }
}
