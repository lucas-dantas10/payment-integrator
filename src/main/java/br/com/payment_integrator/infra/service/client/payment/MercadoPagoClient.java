package br.com.payment_integrator.infra.service.client.payment;

import br.com.payment_integrator.infra.dto.mercado_pago.request.payment.create_payment.CreatePaymentRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "mercado-pago-client", url = "${payments.gateway.mercado_pago.url}")
public interface MercadoPagoClient {

    @PostMapping(value = "/payments",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void createPayment(@RequestHeader("Authorization") String token,
                                           @RequestHeader("X-Idempotency-Key") String idempotencyKey,
                                           @RequestBody CreatePaymentRequestDTO dto);
}
