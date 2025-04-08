package br.com.payment_integrator.application.service.payment;

import br.com.payment_integrator.adapter.service.payment_gateway.PaymentGateway;
import br.com.payment_integrator.domain.entity.financial.Customer;
import br.com.payment_integrator.domain.entity.financial.Payment;
import br.com.payment_integrator.domain.enums.StatusPaymentEnum;
import br.com.payment_integrator.domain.exception.payment.PaymentNotFoundException;
import br.com.payment_integrator.domain.service.payment.IProcessPaymentService;
import br.com.payment_integrator.domain.service.payment_log.ICreatePaymentLogService;
import br.com.payment_integrator.infra.repository.financial.CustomerRepository;
import br.com.payment_integrator.infra.repository.financial.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProcessPaymentService implements IProcessPaymentService {

    private final PaymentRepository paymentRepository;
    private final CustomerRepository customerRepository;
    private final ICreatePaymentLogService createPaymentLogService;
    private final PaymentGateway paymentGateway;

    @Override
    public void processPayment(String paymentId) {
        Payment payment = paymentRepository.findById(UUID.fromString(paymentId))
                .orElseThrow(PaymentNotFoundException::new);
        Customer customer = customerRepository.findByPaymentId(UUID.fromString(paymentId))
                .orElseThrow(PaymentNotFoundException::new);

        paymentGateway.createPayment(payment, customer);

        payment.setStatus(StatusPaymentEnum.APPROVED);
        payment.setUpdatedStatusAt(LocalDateTime.now());

        paymentRepository.save(payment);

        createPaymentLogService.createPaymentLog(payment, "Pagamento processado com sucesso");
    }
}
