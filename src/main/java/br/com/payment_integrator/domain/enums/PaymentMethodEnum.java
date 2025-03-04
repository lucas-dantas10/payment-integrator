package br.com.payment_integrator.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentMethodEnum {
    PIX("pix"),
    BOLETO("boleto"),
    CREDIT_CARD("credit_card");

    private final String value;

    PaymentMethodEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    @JsonCreator
    public static PaymentMethodEnum fromValue(String value) {
        for (PaymentMethodEnum status : PaymentMethodEnum.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }

        throw new IllegalArgumentException("Método de pagamento inválido: " + value);
    }
}
