package br.com.payment_integrator.application.service.invoice;

import br.com.payment_integrator.application.util.AuthenticatedAccount;
import br.com.payment_integrator.domain.builder.invoice.InvoiceResponseBuilder;
import br.com.payment_integrator.domain.dto.invoice.response.FindInvoiceById.InvoiceDetailsResponseDTO;
import br.com.payment_integrator.domain.entity.authentication.Account;
import br.com.payment_integrator.domain.entity.financial.Invoice;
import br.com.payment_integrator.domain.exception.invoice.InvoiceNotFoundException;
import br.com.payment_integrator.domain.service.invoice.IFindInvoiceByIdService;
import br.com.payment_integrator.infra.repository.financial.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindInvoiceByIdService implements IFindInvoiceByIdService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceResponseBuilder invoiceResponseBuilder;

    @Override
    public InvoiceDetailsResponseDTO execute(String id) {
        Account account = AuthenticatedAccount.get();
        Invoice invoice = invoiceRepository.findByIdAndAccount(UUID.fromString(id), account).orElseThrow(InvoiceNotFoundException::new);

        return invoiceResponseBuilder.build(invoice);
    }
}
