package br.com.payment_integrator.domain.exception.payment;

import br.com.payment_integrator.domain.exception.BaseException;
import org.springframework.http.HttpStatus;

public class PaymentNotFoundException extends BaseException {

    private static final String MESSAGE = "Pagamento não encontrado";

    public PaymentNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
