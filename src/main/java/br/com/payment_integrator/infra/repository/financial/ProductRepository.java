package br.com.payment_integrator.infra.repository.financial;

import br.com.payment_integrator.domain.entity.financial.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
