package br.com.payment_integrator.domain.exception.account;

import br.com.payment_integrator.domain.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AccountAlreadyExistException extends BaseException {

    private static final String MESSAGE = "Usuário já existe";

    public AccountAlreadyExistException() {
        super(MESSAGE);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }
}
