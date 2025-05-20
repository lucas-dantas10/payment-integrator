package br.com.payment_integrator.domain.enums;

public enum LogMessagesEnum {

    FATURA_CRIADA("Fatura do nosso serviço criada com ID %s para cliente %s. Valor: %s %s");

    private final String message;

    LogMessagesEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
