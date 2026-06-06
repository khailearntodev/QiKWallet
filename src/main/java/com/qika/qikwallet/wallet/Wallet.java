package com.qika.qikwallet.wallet;

import com.qika.qikwallet.ledger.Ledger;
import com.qika.qikwallet.reconciliation.Reconciliation;
import com.qika.qikwallet.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wallets")
public class Wallet {
    @Id
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "balance", nullable = false, precision = 15, scale = 2)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private WalletStatusEnum status;

    @Column(name = "currency", nullable = false)
    private String currency;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ledger> ledgers = new ArrayList<>();

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
    private List<Reconciliation> reconciliations = new ArrayList<>();
}
