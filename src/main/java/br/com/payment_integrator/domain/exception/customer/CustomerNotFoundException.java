package br.com.payment_integrator.domain.exception.customer;

import br.com.payment_integrator.domain.exception.BaseException;
import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends BaseException {

    private static final String MESSAGE = "Cliente não encontrado";

    public CustomerNotFoundException() {
        super(MESSAGE);
    }
    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
