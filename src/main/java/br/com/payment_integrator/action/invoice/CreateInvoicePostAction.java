package br.com.payment_integrator.action.invoice;

import br.com.payment_integrator.domain.dto.invoice.request.create_payment.CreateInvoiceDTO;
import br.com.payment_integrator.domain.dto.invoice.response.InvoiceResponseDTO;
import br.com.payment_integrator.domain.service.invoice.ICreateInvoiceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/invoice")
@RequiredArgsConstructor
public class CreateInvoicePostAction {

    private final ICreateInvoiceService createInvoiceService;

    @PostMapping
    @Tag(name = "Payment")
    public ResponseEntity<InvoiceResponseDTO> createPayment(
            @RequestBody @Valid CreateInvoiceDTO createInvoiceDTO) throws Exception {
        return ResponseEntity.ok(createInvoiceService.createInvoice(createInvoiceDTO));
    }
}
