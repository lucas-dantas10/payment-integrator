package br.com.payment_integrator.domain.service.invoice;

import br.com.payment_integrator.domain.dto.invoice.response.FindInvoiceById.InvoiceDetailsResponseDTO;

public interface IFindInvoiceByIdService {

    InvoiceDetailsResponseDTO findById(String id);
}
