package br.com.payment_integrator.domain.exception.user;

import br.com.payment_integrator.domain.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistException extends BaseException {

    private static final String MESSAGE = "Usuário já existe";

    public UserAlreadyExistException() {
        super(MESSAGE);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }
}
