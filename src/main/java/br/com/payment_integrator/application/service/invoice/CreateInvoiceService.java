package br.com.payment_integrator.application.service.invoice;

import br.com.payment_integrator.domain.dto.invoice.request.create_payment.CreateInvoiceDTO;
import br.com.payment_integrator.domain.dto.invoice.response.InvoiceResponseDTO;
import br.com.payment_integrator.domain.entity.authentication.Account;
import br.com.payment_integrator.domain.entity.financial.Invoice;
import br.com.payment_integrator.domain.enums.StatusPaymentEnum;
import br.com.payment_integrator.domain.service.customer.ICreateCustomerService;
import br.com.payment_integrator.domain.service.invoice.ICreateInvoiceService;
import br.com.payment_integrator.domain.service.payment_log.ICreatePaymentLogService;
import br.com.payment_integrator.domain.service.product.ICreateProductService;
import br.com.payment_integrator.domain.service.user.IFindAccountByIdService;
import br.com.payment_integrator.adapter.service.rabbitmq.producer.InvoiceProducerGateway;
import br.com.payment_integrator.infra.repository.financial.InvoiceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateInvoiceService implements ICreateInvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final IFindAccountByIdService findUserByIdService;
    private final InvoiceProducerGateway invoiceProducerGateway;
    private final ICreatePaymentLogService createPaymentLogService;
    private final ICreateCustomerService createCustomerService;
    private final ICreateProductService createProductService;

    @Override
    @Transactional
    public InvoiceResponseDTO createInvoice(CreateInvoiceDTO createInvoiceDTO) throws Exception {
        Account account = findUserByIdService.findAccountById("366097e2-4fa1-447c-99b7-71478dd3a993");
        // TODO: adicionar no account o balance atual com relação aos invoices

        Invoice invoice = Invoice.builder()
            .account(account)
            .amount(createInvoiceDTO.totalAmount())
            .status(StatusPaymentEnum.PENDING)
            .paymentMethod(createInvoiceDTO.paymentMethod())
            .currency(createInvoiceDTO.currency().name())
            .build();

        invoiceRepository.save(invoice);

        createCustomerService.createCustomer(createInvoiceDTO.customer(), invoice);

        createInvoiceDTO.products().forEach(productDTO -> createProductService.createProduct(productDTO, invoice));

        invoiceProducerGateway.sendInvoiceForCreation(invoice.getId());

        createPaymentLogService.createPaymentLog(invoice, "Fatura do serviço criada com sucesso");

        return InvoiceResponseDTO.builder()
                .id(invoice.getId())
                .amount(invoice.getAmount())
                .status(invoice.getStatus())
                .paymentMethod(invoice.getPaymentMethod())
                .build();
    }
}
