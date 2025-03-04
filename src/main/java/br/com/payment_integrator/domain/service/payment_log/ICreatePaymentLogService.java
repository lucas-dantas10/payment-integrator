package br.com.payment_integrator.domain.service.payment_log;

import br.com.payment_integrator.domain.entity.financial.Payment;

public interface ICreatePaymentLogService {

    void createPaymentLog(Payment payment, String message);
}
