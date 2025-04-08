package br.com.payment_integrator.application.service.payment;

import br.com.payment_integrator.adapter.service.payment_gateway.PaymentGateway;
import br.com.payment_integrator.domain.entity.financial.Customer;
import br.com.payment_integrator.domain.entity.financial.Invoice;
import br.com.payment_integrator.domain.enums.StatusPaymentEnum;
import br.com.payment_integrator.domain.exception.payment.PaymentNotFoundException;
import br.com.payment_integrator.domain.service.invoice.IProcessPaymentService;
import br.com.payment_integrator.domain.service.payment_log.ICreatePaymentLogService;
import br.com.payment_integrator.infra.repository.financial.CustomerRepository;
import br.com.payment_integrator.infra.repository.financial.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProcessPaymentService implements IProcessPaymentService {

    private final InvoiceRepository invoiceRepository;
    private final CustomerRepository customerRepository;
    private final ICreatePaymentLogService createPaymentLogService;
    private final PaymentGateway paymentGateway;

    @Override
    public void processPayment(String invoiceId) {
        Invoice invoice = invoiceRepository.findById(UUID.fromString(invoiceId))
                .orElseThrow(PaymentNotFoundException::new);
        Customer customer = customerRepository.findByInvoiceId(UUID.fromString(invoiceId))
                .orElseThrow(PaymentNotFoundException::new);

        paymentGateway.createPayment(invoice, customer);

        invoice.setStatus(StatusPaymentEnum.APPROVED);
        invoice.setUpdatedStatusAt(LocalDateTime.now());

        invoiceRepository.save(invoice);

        createPaymentLogService.createPaymentLog(invoice, "Pagamento processado com sucesso");
    }
}
