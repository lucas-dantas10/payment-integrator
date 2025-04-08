package br.com.payment_integrator.domain.exception.invoice;

import br.com.payment_integrator.domain.exception.BaseException;
import org.springframework.http.HttpStatus;

public class InvoiceNotFoundException extends BaseException {

    private static final String MESSAGE = "Fatura não encontrado";

    public InvoiceNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
