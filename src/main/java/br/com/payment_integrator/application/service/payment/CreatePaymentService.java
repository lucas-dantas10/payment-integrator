package br.com.payment_integrator.application.service.payment;

import br.com.payment_integrator.domain.dto.payment.request.create_payment.CreatePaymentDTO;
import br.com.payment_integrator.domain.dto.payment.response.PaymentResponseDTO;
import br.com.payment_integrator.domain.entity.authentication.User;
import br.com.payment_integrator.domain.entity.financial.Payment;
import br.com.payment_integrator.domain.enums.StatusPaymentEnum;
import br.com.payment_integrator.domain.exception.user.UserNotFoundException;
import br.com.payment_integrator.domain.service.payment.ICreatePaymentService;
import br.com.payment_integrator.gateway.service.rabbitmq.producer.PaymentProducerGateway;
import br.com.payment_integrator.infra.repository.authentication.UserRepository;
import br.com.payment_integrator.infra.repository.financial.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreatePaymentService implements ICreatePaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final PaymentProducerGateway paymentProducerGateway;

    @Override
    @Transactional
    public PaymentResponseDTO createPayment(CreatePaymentDTO createPaymentDTO) throws Exception {
        User user = userRepository
                .findById(UUID.fromString(createPaymentDTO.userId()))
                .orElseThrow(UserNotFoundException::new);

        Payment payment = Payment.builder()
            .user(user)
            .amount(createPaymentDTO.amount())
            .status(StatusPaymentEnum.PENDING)
            .paymentMethod(createPaymentDTO.paymentMethod())
            .currency(createPaymentDTO.currency())
            .build();

        paymentRepository.save(payment);

        paymentProducerGateway.sendPaymentForProcessing(payment.getId());

        return PaymentResponseDTO.builder()
                .id(payment.getId())
                .amount(payment.getAmount())
                .status(payment.getStatus())
                .paymentMethod(payment.getPaymentMethod())
                .build();
    }
}
