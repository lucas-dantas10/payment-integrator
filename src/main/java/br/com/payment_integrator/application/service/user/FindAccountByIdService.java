package br.com.payment_integrator.application.service.user;

import br.com.payment_integrator.domain.entity.authentication.Account;
import br.com.payment_integrator.domain.exception.user.UserNotFoundException;
import br.com.payment_integrator.domain.service.user.IFindAccountByIdService;
import br.com.payment_integrator.infra.repository.authentication.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindAccountByIdService implements IFindAccountByIdService {

    private final AccountRepository accountRepository;

    @Override
    public Account findAccountById(String accountId) throws UserNotFoundException {
        return accountRepository
                .findById(UUID.fromString(accountId))
                .orElseThrow(UserNotFoundException::new);
    }
}
