package br.com.payment_integrator.domain.entity.financial;

import br.com.payment_integrator.domain.enums.StatusTransactionEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "financial", name = "tb_transaction")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTransactionEnum status;

    @Column(name = "gateway_response", nullable = false)
    private String gatewayResponse;

    @CreationTimestamp
    @Column(name = "transaction_date_time", nullable = false)
    private LocalDateTime transactionDateTime;
}
