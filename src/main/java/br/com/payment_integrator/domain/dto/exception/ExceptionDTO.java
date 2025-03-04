package br.com.payment_integrator.domain.dto.exception;

import org.springframework.http.HttpStatus;

public record ExceptionDTO(Integer status, String message) {
}
