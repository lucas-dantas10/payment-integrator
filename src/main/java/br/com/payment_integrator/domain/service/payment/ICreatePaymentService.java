package br.com.payment_integrator.domain.service.payment;

import br.com.payment_integrator.domain.dto.payment.request.create_payment.CreatePaymentDTO;
import br.com.payment_integrator.domain.dto.payment.response.PaymentResponseDTO;

public interface ICreatePaymentService {
    PaymentResponseDTO createPayment(CreatePaymentDTO createPaymentDTO) throws Exception;
}
