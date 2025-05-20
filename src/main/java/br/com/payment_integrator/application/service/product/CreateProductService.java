package br.com.payment_integrator.application.service.product;

import br.com.payment_integrator.domain.dto.invoice.request.create_payment.ProductDTO;
import br.com.payment_integrator.domain.entity.financial.Invoice;
import br.com.payment_integrator.domain.entity.financial.Product;
import br.com.payment_integrator.domain.service.product.ICreateProductService;
import br.com.payment_integrator.infra.repository.financial.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateProductService implements ICreateProductService {

    private final ProductRepository productRepository;

    @Transactional
    public void createProduct(ProductDTO productDTO, Invoice invoice) {
        Product product = Product.builder()
                .invoice(invoice)
                .name(productDTO.name())
                .description(productDTO.description())
                .price(productDTO.price())
                .quantity(productDTO.quantity())
                .createdAt(LocalDateTime.now())
                .build();

        productRepository.save(product);
    }
}
