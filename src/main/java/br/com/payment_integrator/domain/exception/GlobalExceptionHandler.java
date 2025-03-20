package br.com.payment_integrator.domain.exception;

import br.com.payment_integrator.domain.dto.exception.ExceptionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> exception(Exception exception) {
        log.error(exception.getMessage(), exception);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ExceptionDTO(HttpStatus.BAD_REQUEST.value(), "Ocorreu um erro inesperado!", null)
        );
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ExceptionDTO> handleBaseException(BaseException exception) {
        log.error(exception.getMessage(), exception);

        return ResponseEntity.status(exception.getHttpStatus()).body(
                new ExceptionDTO(exception.getHttpStatus().value(), exception.getMessage(), null)
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDTO> methodArgumentNotValidException (MethodArgumentNotValidException exception) {
        log.error(exception.getMessage(), exception);

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ExceptionDTO exceptionDTO = new ExceptionDTO(
                exception.getStatusCode().value(),
                "Erro na requisição!",
                errors
        );

        return ResponseEntity.status(exception.getStatusCode().value()).body(exceptionDTO);
    }
}
