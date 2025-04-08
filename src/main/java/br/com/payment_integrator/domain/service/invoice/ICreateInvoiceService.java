package br.com.payment_integrator.domain.service.invoice;

import br.com.payment_integrator.domain.dto.invoice.request.create_payment.CreateInvoiceDTO;
import br.com.payment_integrator.domain.dto.invoice.response.InvoiceResponseDTO;

public interface ICreateInvoiceService {
    InvoiceResponseDTO createInvoice(CreateInvoiceDTO createInvoiceDTO) throws Exception;
}
