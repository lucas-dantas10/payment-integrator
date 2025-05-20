package br.com.payment_integrator.unit.application.service.payment_log;

import br.com.payment_integrator.application.service.payment_log.CreatePaymentLogService;
import br.com.payment_integrator.domain.entity.financial.Invoice;
import br.com.payment_integrator.domain.entity.log.PaymentLog;
import br.com.payment_integrator.infra.repository.log.PaymentLogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreatePaymentLogServiceTest {

    @InjectMocks
    private CreatePaymentLogService createPaymentLogService;

    @Mock
    private PaymentLogRepository paymentLogRepository;

    @Test
    public void shouldCreatePaymentLog() {
        Invoice invoice = new Invoice();
        String message = "Test message log";

        PaymentLog paymentLog = PaymentLog.builder()
                .invoice(invoice)
                .message(message)
                .build();

        createPaymentLogService.execute(invoice, message);

        ArgumentCaptor<PaymentLog> captor = ArgumentCaptor.forClass(PaymentLog.class);
        verify(paymentLogRepository, times(1)).save(captor.capture());

        PaymentLog actual = captor.getValue();

        assertNotNull(actual);
        assertEquals(paymentLog.getInvoice(), actual.getInvoice());
        assertEquals(paymentLog.getMessage(), actual.getMessage());
    }
}
