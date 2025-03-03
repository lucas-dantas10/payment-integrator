package br.com.payment_integrator.domain.service.payment;

import br.com.payment_integrator.domain.entity.financial.Payment;

import java.math.BigDecimal;
import java.util.UUID;

public interface ICreatePaymentService {
    Payment createPayment(UUID userId, BigDecimal amount) throws Exception;
}
