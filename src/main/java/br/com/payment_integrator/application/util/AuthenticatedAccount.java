package br.com.payment_integrator.application.util;

import br.com.payment_integrator.domain.entity.authentication.Account;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticatedAccount {

    public static Account get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof Account)) {
            throw new RuntimeException("Conta não autenticada");
        }

        return (Account) authentication.getPrincipal();
    }
}
