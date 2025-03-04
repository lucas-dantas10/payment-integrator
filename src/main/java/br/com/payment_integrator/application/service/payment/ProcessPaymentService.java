package br.com.payment_integrator.application.service.payment;

import br.com.payment_integrator.domain.entity.financial.Payment;
import br.com.payment_integrator.domain.enums.StatusPaymentEnum;
import br.com.payment_integrator.domain.service.payment.IProcessPaymentService;
import br.com.payment_integrator.infra.repository.financial.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProcessPaymentService implements IProcessPaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(String paymentId) {
        Payment payment = paymentRepository.findById(UUID.fromString(paymentId)).orElseThrow();

        // TODO: chamar gateway de pagamento

        payment.setStatus(StatusPaymentEnum.APPROVED);

        paymentRepository.save(payment);
    }
}
