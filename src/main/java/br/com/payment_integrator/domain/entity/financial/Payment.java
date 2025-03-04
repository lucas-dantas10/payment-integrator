package br.com.payment_integrator.domain.entity.financial;

import br.com.payment_integrator.domain.entity.authentication.User;
import br.com.payment_integrator.domain.enums.PaymentMethodEnum;
import br.com.payment_integrator.domain.enums.StatusPaymentEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "financial", name = "tb_payment")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(length = '3', nullable = false)
    private String currency = "BRL";

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPaymentEnum status = StatusPaymentEnum.PENDING;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethodEnum paymentMethod;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_status_at")
    private LocalDateTime updatedStatusAt;
}
