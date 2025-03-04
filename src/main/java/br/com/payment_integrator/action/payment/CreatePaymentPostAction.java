package br.com.payment_integrator.action.payment;

import br.com.payment_integrator.domain.dto.payment.request.create_payment.CreatePaymentDTO;
import br.com.payment_integrator.domain.dto.payment.response.PaymentResponseDTO;
import br.com.payment_integrator.domain.entity.financial.Payment;
import br.com.payment_integrator.domain.service.payment.ICreatePaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class CreatePaymentPostAction {

    private final ICreatePaymentService createPaymentService;

    @PostMapping
    @Tag(name = "Payment")
    public ResponseEntity<PaymentResponseDTO> createPayment(@RequestBody CreatePaymentDTO createPaymentDTO) throws Exception {
        return ResponseEntity.ok(createPaymentService.createPayment(createPaymentDTO));
    }
}
