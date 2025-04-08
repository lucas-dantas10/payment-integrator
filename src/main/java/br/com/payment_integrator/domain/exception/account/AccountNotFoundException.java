package br.com.payment_integrator.domain.exception.account;

import br.com.payment_integrator.domain.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends BaseException {

    private static final String MESSAGE = "Usuário não encontrado";

    public AccountNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
