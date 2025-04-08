package br.com.payment_integrator.infra.service.payment_gateway.mercado_pago;

import br.com.payment_integrator.adapter.service.payment_gateway.PaymentGateway;
import br.com.payment_integrator.domain.entity.financial.Customer;
import br.com.payment_integrator.domain.entity.financial.Payment;
import br.com.payment_integrator.infra.dto.mercado_pago.request.payment.create_payment.CreatePaymentRequestDTO;
import br.com.payment_integrator.infra.dto.mercado_pago.request.payment.create_payment.PayerDTO;
import br.com.payment_integrator.infra.service.client.payment.MercadoPagoClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MercadoPagoService implements PaymentGateway
{

    @Value("Bearer ${payments.gateway.mercado_pago.token}")
    private String token;

    @Value("${payments.gateway.mercado_pago.idempotency-key}")
    private String idempotencyKey;

    private final MercadoPagoClient mercadoPagoClient;

    @Override
    public void createPayment(Payment payment, Customer customer) {
        log.info("Start creating payment");

        PayerDTO payerDTO = PayerDTO.builder()
                .email(customer.getEmail())
                .build();
        CreatePaymentRequestDTO requestDTO = CreatePaymentRequestDTO.builder()
                .transactionAmount(payment.getAmount())
                .paymentMethodId(payment.getPaymentMethod().name())
                .payer(payerDTO)
                .build();

        mercadoPagoClient.createPayment(token, idempotencyKey, requestDTO);

        log.info("Finish create payment");
    }
}
