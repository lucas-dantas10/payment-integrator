package br.com.payment_integrator.application.service.payment;

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
import br.com.payment_integrator.adapter.service.rabbitmq.producer.PaymentProducerGateway;
import br.com.payment_integrator.infra.repository.financial.InvoiceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateInvoiceService implements ICreateInvoiceService {

    private final InvoiceRepository paymentRepository;
    private final IFindAccountByIdService findUserByIdService;
    private final PaymentProducerGateway paymentProducerGateway;
    private final ICreatePaymentLogService createPaymentLogService;
    private final ICreateCustomerService createCustomerService;
    private final ICreateProductService createProductService;

    @Override
    @Transactional
    public InvoiceResponseDTO createInvoice(CreateInvoiceDTO createPaymentDTO) throws Exception {
        Account account = findUserByIdService.findAccountById(createPaymentDTO.userId());

        Invoice invoice = Invoice.builder()
            .account(account)
            .amount(createPaymentDTO.totalAmount())
            .status(StatusPaymentEnum.PENDING)
            .paymentMethod(createPaymentDTO.paymentMethod())
            .currency(createPaymentDTO.currency().name())
            .build();

        paymentRepository.save(invoice);

        createCustomerService.createCustomer(createPaymentDTO.customer(), invoice);

        createPaymentDTO.products().forEach(productDTO -> createProductService.createProduct(productDTO, invoice));

        paymentProducerGateway.sendPaymentForProcessing(invoice.getId());

        createPaymentLogService.createPaymentLog(invoice, "Pagamento criado com sucesso");

        return InvoiceResponseDTO.builder()
                .id(invoice.getId())
                .amount(invoice.getAmount())
                .status(invoice.getStatus())
                .paymentMethod(invoice.getPaymentMethod())
                .build();
    }
}
