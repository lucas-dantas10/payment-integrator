package br.com.payment_integrator.unit.factory.entity.account;

import br.com.payment_integrator.domain.entity.authentication.Account;

import java.math.BigDecimal;
import java.util.UUID;

public class AccountMock {

    public static Account createAccountWithId(UUID accountId, String name, String email, String apiKey) {
        return Account.builder()
                .id(accountId)
                .name(name)
                .email(email)
                .apiKey(apiKey)
                .balance(BigDecimal.ZERO)
                .build();
    }

    public static Account createAccountWithId(String name, String email, String apiKey) {
        UUID accountId = UUID.randomUUID();

        return Account.builder()
                .id(accountId)
                .name(name)
                .email(email)
                .apiKey(apiKey)
                .balance(BigDecimal.ZERO)
                .build();
    }

    public static Account createAccountWithoutId(String name, String email, String apiKey) {
        return Account.builder()
                .name(name)
                .email(email)
                .apiKey(apiKey)
                .balance(BigDecimal.ZERO)
                .build();
    }
}
