package br.com.payment_integrator.unit.application.service.account;

import br.com.payment_integrator.application.service.account.FindAccountByIdService;
import br.com.payment_integrator.domain.entity.authentication.Account;
import br.com.payment_integrator.domain.exception.account.AccountNotFoundException;
import br.com.payment_integrator.infra.repository.authentication.AccountRepository;
import br.com.payment_integrator.unit.factory.entity.account.AccountMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FindAccountByIdServiceTest
{

    @InjectMocks
    private FindAccountByIdService findAccountByIdService;

    @Mock
    private AccountRepository accountRepository;

    @Test
    public void shouldFindAccountById() {
        UUID accountId = UUID.randomUUID();
        Account account = AccountMock.createAccountWithId(accountId, "Teste", "teste@email.com", "teste");

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        Account accountResponse = findAccountByIdService.execute(accountId.toString());

        verify(accountRepository, times(1)).findById(accountId);
        assertNotNull(accountResponse);
        assertEquals(accountId, accountResponse.getId());
        assertEquals(account.getName(), accountResponse.getName());
        assertEquals(account.getEmail(), accountResponse.getEmail());
        assertEquals(account.getApiKey(), accountResponse.getApiKey());
        assertEquals(account.getBalance(), accountResponse.getBalance());
    }

    @Test
    public void shouldThrowExceptionWhenAccountNotFound() {
        UUID accountId = UUID.randomUUID();
        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> findAccountByIdService.execute(accountId.toString()));
    }
}