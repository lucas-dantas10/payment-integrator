package br.com.payment_integrator.application.service.payment;

import br.com.payment_integrator.domain.dto.payment.request.create_payment.CreatePaymentDTO;
import br.com.payment_integrator.domain.dto.payment.response.PaymentResponseDTO;
import br.com.payment_integrator.domain.entity.authentication.User;
import br.com.payment_integrator.domain.entity.financial.Payment;
import br.com.payment_integrator.domain.enums.StatusPaymentEnum;
import br.com.payment_integrator.domain.service.customer.ICreateCustomerService;
import br.com.payment_integrator.domain.service.payment.ICreatePaymentService;
import br.com.payment_integrator.domain.service.payment_log.ICreatePaymentLogService;
import br.com.payment_integrator.domain.service.product.ICreateProductService;
import br.com.payment_integrator.domain.service.user.IFindUserByIdService;
import br.com.payment_integrator.gateway.service.rabbitmq.producer.PaymentProducerGateway;
import br.com.payment_integrator.infra.repository.financial.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePaymentService implements ICreatePaymentService {

    private final PaymentRepository paymentRepository;
    private final IFindUserByIdService findUserByIdService;
    private final PaymentProducerGateway paymentProducerGateway;
    private final ICreatePaymentLogService createPaymentLogService;
    private final ICreateCustomerService createCustomerService;
    private final ICreateProductService createProductService;

    @Override
    @Transactional
    public PaymentResponseDTO createPayment(CreatePaymentDTO createPaymentDTO) throws Exception {
        User user = findUserByIdService.findUserById(createPaymentDTO.userId());

        Payment payment = Payment.builder()
            .user(user)
            .amount(createPaymentDTO.totalAmount())
            .status(StatusPaymentEnum.PENDING)
            .paymentMethod(createPaymentDTO.paymentMethod())
            .currency(createPaymentDTO.currency().name())
            .build();

        paymentRepository.save(payment);

        createCustomerService.createCustomer(createPaymentDTO.customer(), payment);

        createPaymentDTO.products().forEach(productDTO -> createProductService.createProduct(productDTO, payment));

        paymentProducerGateway.sendPaymentForProcessing(payment.getId());

        createPaymentLogService.createPaymentLog(payment, "Pagamento criado com sucesso");

        return PaymentResponseDTO.builder()
                .id(payment.getId())
                .amount(payment.getAmount())
                .status(payment.getStatus())
                .paymentMethod(payment.getPaymentMethod())
                .build();
    }
}
