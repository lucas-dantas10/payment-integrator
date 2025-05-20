package br.com.payment_integrator.application.service.invoice;

import br.com.payment_integrator.domain.dto.invoice.request.create_payment.CreateInvoiceDTO;
import br.com.payment_integrator.domain.dto.invoice.response.InvoiceResponseDTO;
import br.com.payment_integrator.domain.entity.authentication.Account;
import br.com.payment_integrator.domain.entity.financial.Invoice;
import br.com.payment_integrator.domain.event.invoice.InvoiceCreatedEvent;
import br.com.payment_integrator.domain.service.customer.ICreateCustomerService;
import br.com.payment_integrator.domain.service.invoice.ICreateInvoiceService;
import br.com.payment_integrator.domain.service.payment_log.ICreatePaymentLogService;
import br.com.payment_integrator.domain.service.product.ICreateProductService;
import br.com.payment_integrator.domain.service.user.IFindAccountByIdService;
import br.com.payment_integrator.adapter.service.rabbitmq.producer.InvoiceProducerGateway;
import br.com.payment_integrator.infra.repository.financial.InvoiceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateInvoiceService implements ICreateInvoiceService {
    private static final String LOG_MESSAGE_INVOICE = "Fatura do nosso serviço criada com ID %s para cliente %s. Valor: %s %s";

    private final InvoiceRepository invoiceRepository;
    private final IFindAccountByIdService findUserByIdService;
    private final InvoiceProducerGateway invoiceProducerHandler;
    private final ICreatePaymentLogService createPaymentLogService;
    private final ICreateCustomerService createCustomerService;
    private final ICreateProductService createProductService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Transactional
    public InvoiceResponseDTO createInvoice(CreateInvoiceDTO createInvoiceDTO) {
        Account account = findUserByIdService.execute("366097e2-4fa1-447c-99b7-71478dd3a993");

        Invoice invoice = Invoice.create(
                account,
                createInvoiceDTO.totalAmount(),
                createInvoiceDTO.paymentMethod(),
                createInvoiceDTO.currency().name());

        invoiceRepository.save(invoice);

        createCustomerService.execute(createInvoiceDTO.customer(), invoice);

        createInvoiceDTO.products().forEach(productDTO -> createProductService.createProduct(productDTO, invoice));

        log.info("Fatura criada com ID {} para cliente {}. Valor: {} {}",
                invoice.getId(), createInvoiceDTO.customer().name(), invoice.getAmount(), invoice.getCurrency());

        applicationEventPublisher.publishEvent(new InvoiceCreatedEvent(invoice.getId()));

        String logMessageInvoiceFormatted = String.format(LOG_MESSAGE_INVOICE,
                invoice.getId(),
                createInvoiceDTO.customer().name(),
                invoice.getAmount(),
                invoice.getCurrency());

        createPaymentLogService.execute(invoice, logMessageInvoiceFormatted);

        return InvoiceResponseDTO.builder()
                .id(invoice.getId())
                .amount(invoice.getAmount())
                .status(invoice.getStatus())
                .paymentMethod(invoice.getPaymentMethod())
                .build();
    }
}
