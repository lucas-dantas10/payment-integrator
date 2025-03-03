package br.com.payment_integrator.infra.repository.financial;

import br.com.payment_integrator.domain.entity.financial.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
