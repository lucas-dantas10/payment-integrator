package br.com.payment_integrator.unit.application.service.product;

import br.com.payment_integrator.application.service.product.CreateProductService;
import br.com.payment_integrator.domain.dto.invoice.request.create_payment.ProductDTO;
import br.com.payment_integrator.domain.entity.financial.Invoice;
import br.com.payment_integrator.domain.entity.financial.Product;
import br.com.payment_integrator.infra.repository.financial.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateProductServiceTest {

    @InjectMocks
    private CreateProductService createProductService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void shouldCreateProduct() {
        ProductDTO productDTO = new ProductDTO("Test Product", "Test Description", new BigDecimal("99.99"), 2);
        Invoice invoice = new Invoice();

        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);

        createProductService.execute(productDTO, invoice);

        verify(productRepository).save(productCaptor.capture());
        Product savedProduct = productCaptor.getValue();

        assertEquals("Test Product", savedProduct.getName());
        assertEquals("Test Description", savedProduct.getDescription());
        assertEquals(new BigDecimal("99.99"), savedProduct.getPrice());
        assertEquals(2, savedProduct.getQuantity());
        assertEquals(invoice, savedProduct.getInvoice());
        assertNotNull(savedProduct.getCreatedAt());
    }
}
