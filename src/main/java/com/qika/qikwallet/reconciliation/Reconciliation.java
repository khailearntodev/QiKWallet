package com.qika.qikwallet.reconciliation;

import com.qika.qikwallet.wallet.Wallet;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "reconciliations")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reconciliation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReconciliationStatusEnum status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    @Column(name = "reconciliation_date", nullable = false)
    private ZonedDateTime reconciliationDate;

    @Column(name = "expected_balance", nullable = false, precision = 15, scale = 2)
    private BigDecimal expectedBalance;

    @Column(name = "actual_balance", nullable = false, precision = 15, scale = 2)
    private BigDecimal actualBalance;

    @Column(name = "difference", nullable = false, precision = 15, scale = 2)
    private BigDecimal difference;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;
}
