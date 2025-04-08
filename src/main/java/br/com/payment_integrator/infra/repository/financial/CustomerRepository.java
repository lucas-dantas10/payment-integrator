package br.com.payment_integrator.infra.repository.financial;

import br.com.payment_integrator.domain.entity.financial.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Optional<Customer> findByPaymentId(UUID paymentId);
}
