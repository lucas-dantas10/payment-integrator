package br.com.payment_integrator.infra.repository.authentication;

import br.com.payment_integrator.domain.entity.authentication.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    Optional<Account> findByEmail(String email);
    Optional<Account> findByApiKey(String apiKey);
}
