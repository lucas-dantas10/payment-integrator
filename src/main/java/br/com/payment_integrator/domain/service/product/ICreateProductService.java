package br.com.payment_integrator.domain.service.product;

import br.com.payment_integrator.domain.dto.payment.request.create_payment.ProductDTO;
import br.com.payment_integrator.domain.entity.financial.Invoice;

public interface ICreateProductService {

    void createProduct(ProductDTO productDTO, Invoice invoice);
}
