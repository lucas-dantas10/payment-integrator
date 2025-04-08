package br.com.payment_integrator.application.service.payment;

import br.com.payment_integrator.adapter.service.payment_gateway.PaymentGateway;
import br.com.payment_integrator.domain.entity.financial.Customer;
import br.com.payment_integrator.domain.entity.financial.Invoice;
import br.com.payment_integrator.domain.exception.customer.CustomerNotFoundException;
import br.com.payment_integrator.domain.exception.invoice.InvoiceNotFoundException;
import br.com.payment_integrator.domain.service.invoice.ICreateInvoiceGatewayService;
import br.com.payment_integrator.domain.service.payment_log.ICreatePaymentLogService;
import br.com.payment_integrator.infra.repository.financial.CustomerRepository;
import br.com.payment_integrator.infra.repository.financial.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateInvoiceGatewayService implements ICreateInvoiceGatewayService {

    private final InvoiceRepository invoiceRepository;
    private final CustomerRepository customerRepository;
    private final ICreatePaymentLogService createPaymentLogService;
    private final PaymentGateway paymentGateway;

    @Override
    public void createInvoice(String invoiceId) {
        Invoice invoice = invoiceRepository.findById(UUID.fromString(invoiceId))
                .orElseThrow(InvoiceNotFoundException::new);
        Customer customer = customerRepository.findByInvoiceId(UUID.fromString(invoiceId))
                .orElseThrow(CustomerNotFoundException::new);

        paymentGateway.createPayment(invoice, customer);

        // TODO: Pegar retorno do mercado pago e salvar na tabela de Invoice

        createPaymentLogService.createPaymentLog(invoice, "Fatura criada com sucesso");
    }
}
