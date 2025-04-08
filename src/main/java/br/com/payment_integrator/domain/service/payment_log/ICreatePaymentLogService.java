package br.com.payment_integrator.domain.service.payment_log;

import br.com.payment_integrator.domain.entity.financial.Invoice;

public interface ICreatePaymentLogService {

    void createPaymentLog(Invoice invoice, String message);
}
