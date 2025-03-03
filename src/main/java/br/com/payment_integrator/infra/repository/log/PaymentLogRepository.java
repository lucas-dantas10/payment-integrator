package br.com.payment_integrator.infra.repository.log;

import br.com.payment_integrator.domain.entity.log.PaymentLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentLogRepository extends JpaRepository<PaymentLog, UUID> {
}
