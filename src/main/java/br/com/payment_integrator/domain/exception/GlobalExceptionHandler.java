package br.com.payment_integrator.domain.exception;

import br.com.payment_integrator.domain.dto.exception.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> exception(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ExceptionDTO(HttpStatus.BAD_REQUEST.value(), "Ocorreu um erro inesperado!")
        );
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ExceptionDTO> handleBaseException(BaseException exception) {
        return ResponseEntity.status(exception.getHttpStatus()).body(
                new ExceptionDTO(exception.getHttpStatus().value(), exception.getMessage())
        );
    }
}
