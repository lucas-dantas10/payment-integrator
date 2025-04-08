package br.com.payment_integrator.application.service.payment_log;

import br.com.payment_integrator.domain.entity.financial.Invoice;
import br.com.payment_integrator.domain.entity.log.PaymentLog;
import br.com.payment_integrator.domain.service.payment_log.ICreatePaymentLogService;
import br.com.payment_integrator.infra.repository.log.PaymentLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePaymentLogService implements ICreatePaymentLogService {

    private final PaymentLogRepository paymentLogRepository;

    @Override
    public void createPaymentLog(Invoice invoice, String message) {
        PaymentLog paymentLog = PaymentLog.builder()
                .invoice(invoice)
                .message(message)
                .build();

        paymentLogRepository.save(paymentLog);
    }
}
