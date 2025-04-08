package br.com.payment_integrator.application.service.user;

import br.com.payment_integrator.domain.dto.account.create_account.CreateAccountDTO;
import br.com.payment_integrator.domain.entity.authentication.Account;
import br.com.payment_integrator.domain.exception.user.UserAlreadyExistException;
import br.com.payment_integrator.domain.service.user.ICreateAccountService;
import br.com.payment_integrator.infra.repository.authentication.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateAccountService implements ICreateAccountService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public Account createAccount(CreateAccountDTO createAccountDTO) throws Exception {
        Optional<Account> accountExist = accountRepository.findByEmail(createAccountDTO.email());

        if (accountExist.isPresent()) {
            throw new UserAlreadyExistException();
        }

        Account account = Account.builder()
                .name(createAccountDTO.name())
                .email(createAccountDTO.email())
                .apiKey("teste")
                .build();

        return accountRepository.save(account);
    }
}
