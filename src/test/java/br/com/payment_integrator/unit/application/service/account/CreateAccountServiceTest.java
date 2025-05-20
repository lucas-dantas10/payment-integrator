package br.com.payment_integrator.unit.application.service.account;

import br.com.payment_integrator.application.service.account.CreateAccountService;
import br.com.payment_integrator.domain.dto.account.create_account.CreateAccountDTO;
import br.com.payment_integrator.domain.entity.authentication.Account;
import br.com.payment_integrator.domain.service.api_key.IGenerateApiKeyService;
import br.com.payment_integrator.infra.repository.authentication.AccountRepository;
import br.com.payment_integrator.unit.factory.dto.CreateAccountDTOMock;
import br.com.payment_integrator.unit.factory.entity.account.AccountMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateAccountServiceTest {

    @InjectMocks
    private CreateAccountService createAccountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private IGenerateApiKeyService generateApiKeyService;

    @Test
    public void shouldCreateAccount() throws Exception {
        CreateAccountDTO dto = CreateAccountDTOMock.createAccountDTO();
        Account savedAccount = AccountMock.createAccountWithoutId(dto.name(), dto.email(), "teste");

        when(accountRepository.findByEmail("teste@email.com"))
                .thenReturn(Optional.empty());

        when(generateApiKeyService.execute()).thenReturn("teste");

        when(accountRepository.save(savedAccount))
                .thenReturn(savedAccount);

        Account result = createAccountService.execute(dto);

        assertNotNull(result);
        assertInstanceOf(Account.class, result);
        assertEquals("Teste", result.getName());
        assertEquals("teste@email.com", result.getEmail());
        assertEquals("teste", result.getApiKey());
        assertEquals(BigDecimal.ZERO, result.getBalance());
    }
}