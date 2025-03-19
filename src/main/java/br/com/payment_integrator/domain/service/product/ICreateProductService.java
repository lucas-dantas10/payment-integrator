package br.com.payment_integrator.domain.service.product;

import br.com.payment_integrator.domain.dto.payment.request.create_payment.ProductDTO;
import br.com.payment_integrator.domain.entity.financial.Payment;

import java.util.List;

public interface ICreateProductService {

    void createProduct(ProductDTO productDTO, Payment payment);
}
