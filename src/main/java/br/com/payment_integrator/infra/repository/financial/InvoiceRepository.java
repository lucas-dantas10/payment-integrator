package br.com.payment_integrator.infra.repository.financial;

import br.com.payment_integrator.domain.entity.financial.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
}
