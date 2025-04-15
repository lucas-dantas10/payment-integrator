package br.com.payment_integrator.infra.service.external_invoice_gateway.mercado_pago;

import br.com.payment_integrator.adapter.service.external_invoice_service.ExternalInvoiceService;
import br.com.payment_integrator.domain.entity.financial.Customer;
import br.com.payment_integrator.domain.entity.financial.Invoice;
import br.com.payment_integrator.infra.dto.mercado_pago.request.invoice.create_invoice.CreateInvoiceRequestDTO;
import br.com.payment_integrator.infra.dto.mercado_pago.request.invoice.create_invoice.PayerDTO;
import br.com.payment_integrator.infra.dto.mercado_pago.response.invoice.create_invoice.CreateInvoiceResponseDTO;
import br.com.payment_integrator.infra.service.client.invoice.MercadoPagoClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MercadoPagoService implements ExternalInvoiceService
{

    @Value("Bearer ${payments.gateway.mercado_pago.token}")
    private String token;

    @Value("${payments.gateway.mercado_pago.idempotency-key}")
    private String idempotencyKey;

    private final MercadoPagoClient mercadoPagoClient;

    @Override
    public CreateInvoiceResponseDTO createInvoice(Invoice invoice, Customer customer) {
        log.info("Inicio chamada serviço externo com o invoice {} do cliente {}", invoice.getId(), customer.getName());

        PayerDTO payerDTO = PayerDTO.builder()
                .email(customer.getEmail())
                .build();
        CreateInvoiceRequestDTO requestDTO = CreateInvoiceRequestDTO.builder()
                .transactionAmount(invoice.getAmount())
                .paymentMethodId(invoice.getPaymentMethod().name())
                .payer(payerDTO)
                .build();

        log.info("Finalizada chamada serviço externo com o invoice {} do cliente {}", invoice.getId(), customer.getName());

        return mercadoPagoClient.createInvoice(token, idempotencyKey, requestDTO);
    }
}
