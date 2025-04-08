package br.com.payment_integrator.domain.entity.financial;

import br.com.payment_integrator.domain.entity.authentication.Account;
import br.com.payment_integrator.domain.enums.PaymentMethodEnum;
import br.com.payment_integrator.domain.enums.StatusPaymentEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "financial", name = "tb_invoice")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name = "currency", length = '3', nullable = false)
    private String currency;

    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(nullable = false, columnDefinition = "status_payment")
    private StatusPaymentEnum status = StatusPaymentEnum.PENDING;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethodEnum paymentMethod;

    @Column(name = "payment_external_id")
    private Integer paymentExternalId;

    @Column(name = "pix_qr_code")
    private String pixQrCode;

    @Column(name = "pix_payment_link")
    private String pixPaymentLink;

    @Column(name = "status_details_external")
    private String statusDetailsExternal;

    @Column(name = "gateway_response")
    private String gatewayResponse;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_status_at")
    private LocalDateTime updatedStatusAt;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;
}
