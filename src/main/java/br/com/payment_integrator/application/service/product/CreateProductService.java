package br.com.payment_integrator.application.service.product;

import br.com.payment_integrator.domain.dto.payment.request.create_payment.ProductDTO;
import br.com.payment_integrator.domain.entity.financial.Payment;
import br.com.payment_integrator.domain.entity.financial.Product;
import br.com.payment_integrator.domain.service.product.ICreateProductService;
import br.com.payment_integrator.infra.repository.financial.ProductReposiitory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateProductService implements ICreateProductService {

    private final ProductReposiitory productReposiitory;

    @Transactional
    public void createProduct(ProductDTO productDTO, Payment payment) {
        Product product = Product.builder()
                .payment(payment)
                .name(productDTO.name())
                .description(productDTO.description())
                .price(productDTO.price())
                .quantity(productDTO.quantity())
                .createdAt(LocalDateTime.now())
                .build();

        productReposiitory.save(product);
    }
}
