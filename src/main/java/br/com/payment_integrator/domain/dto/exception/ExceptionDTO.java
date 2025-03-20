package br.com.payment_integrator.domain.dto.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public record ExceptionDTO(Integer status, String message, Map<String, String> errors) {
}
