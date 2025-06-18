package br.com.payment_integrator.application.service.account;

import br.com.payment_integrator.domain.dto.account.create_account.CreateAccountDTO;
import br.com.payment_integrator.domain.entity.authentication.Account;
import br.com.payment_integrator.domain.exception.account.AccountAlreadyExistException;
import br.com.payment_integrator.domain.service.api_key.IGenerateApiKeyService;
import br.com.payment_integrator.domain.service.account.ICreateAccountService;
import br.com.payment_integrator.infra.repository.authentication.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateAccountService implements ICreateAccountService {

    private final AccountRepository accountRepository;
    private final IGenerateApiKeyService generateApiKeyService;

    @Transactional
    public Account execute(CreateAccountDTO createAccountDTO) throws Exception {
        Optional<Account> accountExist = accountRepository.findByEmail(createAccountDTO.email());

        if (accountExist.isPresent()) {
            throw new AccountAlreadyExistException();
        }

        Account account = Account.builder()
                .name(createAccountDTO.name())
                .email(createAccountDTO.email())
                .apiKey(generateApiKeyService.execute())
                .balance(BigDecimal.ZERO)
                .build();

        return accountRepository.save(account);
    }
}
