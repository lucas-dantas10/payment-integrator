package br.com.payment_integrator.infra.service.client.invoice;

import br.com.payment_integrator.infra.dto.mercado_pago.request.invoice.create_invoice.CreateInvoiceRequestDTO;
import br.com.payment_integrator.infra.dto.mercado_pago.response.invoice.create_invoice.CreateInvoiceResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "mercado-pago-client", url = "${payments.gateway.mercado_pago.url}")
public interface MercadoPagoClient {

    @PostMapping(value = "/payments",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    CreateInvoiceResponseDTO createInvoice(@RequestHeader("Authorization") String token,
                                           @RequestHeader("X-Idempotency-Key") String idempotencyKey,
                                           @RequestBody CreateInvoiceRequestDTO dto);
}
