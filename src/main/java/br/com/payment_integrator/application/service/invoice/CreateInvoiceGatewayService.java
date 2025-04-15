package br.com.payment_integrator.application.service.invoice;

import br.com.payment_integrator.adapter.service.external_invoice_service.ExternalInvoiceService;
import br.com.payment_integrator.domain.entity.financial.Customer;
import br.com.payment_integrator.domain.entity.financial.Invoice;
import br.com.payment_integrator.domain.exception.customer.CustomerNotFoundException;
import br.com.payment_integrator.domain.exception.invoice.InvoiceNotFoundException;
import br.com.payment_integrator.domain.service.invoice.ICreateInvoiceGatewayService;
import br.com.payment_integrator.domain.service.payment_log.ICreatePaymentLogService;
import br.com.payment_integrator.infra.dto.mercado_pago.response.invoice.create_invoice.CreateInvoiceResponseDTO;
import br.com.payment_integrator.infra.repository.financial.CustomerRepository;
import br.com.payment_integrator.infra.repository.financial.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateInvoiceGatewayService implements ICreateInvoiceGatewayService {

    private final InvoiceRepository invoiceRepository;
    private final CustomerRepository customerRepository;
    private final ICreatePaymentLogService createPaymentLogService;
    private final ExternalInvoiceService externalInvoiceService;

    @Override
    public void createInvoice(String invoiceId) {
        Invoice invoice = invoiceRepository.findById(UUID.fromString(invoiceId))
                .orElseThrow(InvoiceNotFoundException::new);
        Customer customer = customerRepository.findByInvoiceId(UUID.fromString(invoiceId))
                .orElseThrow(CustomerNotFoundException::new);

        CreateInvoiceResponseDTO invoiceResponseDTO = externalInvoiceService.createInvoice(invoice, customer);

        Invoice invoiceUpdated = invoice.updateWithExternalData(
                invoiceResponseDTO.id(),
                invoiceResponseDTO.pointOfInteractionDTO().transactionDataDTO().qrCode(),
                invoiceResponseDTO.pointOfInteractionDTO().transactionDataDTO().ticketUrl(),
                invoiceResponseDTO.statusDetail(),
                invoiceResponseDTO.toJson()
        );

        invoiceRepository.save(invoiceUpdated);

        log.info("Fatura criada com ID {} do cliente {}. Valor: {} {}",
                invoice.getId(), customer.getName(), invoice.getAmount(), invoice.getCurrency());
    }
}
