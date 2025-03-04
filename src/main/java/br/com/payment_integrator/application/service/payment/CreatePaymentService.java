package br.com.payment_integrator.application.service.payment;

import br.com.payment_integrator.domain.dto.payment.create_payment.CreatePaymentDTO;
import br.com.payment_integrator.domain.entity.authentication.User;
import br.com.payment_integrator.domain.entity.financial.Payment;
import br.com.payment_integrator.domain.enums.StatusPaymentEnum;
import br.com.payment_integrator.domain.service.payment.ICreatePaymentService;
import br.com.payment_integrator.infra.repository.authentication.UserRepository;
import br.com.payment_integrator.infra.repository.financial.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import static org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreatePaymentService implements ICreatePaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Payment createPayment(CreatePaymentDTO createPaymentDTO) throws Exception {
        User user = userRepository
                .findById(UUID.fromString(createPaymentDTO.userId()))
                .orElseThrow(NotFoundException::new);

        Payment payment = Payment.builder()
            .user(user)
            .amount(createPaymentDTO.amount())
            .status(StatusPaymentEnum.PENDING)
            .paymentMethod(createPaymentDTO.paymentMethod())
            .currency(createPaymentDTO.currency())
            .build();

        paymentRepository.save(payment);

        return payment;
    }
}
