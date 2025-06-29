package com.fanaka.kula.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan_transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_detail_id")
    private Long paymentDetailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id", nullable = false)
    private Loan loan;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(columnDefinition = "TEXT")
    private String name;

    @Column(nullable = false, precision = 65, scale = 6)
    private BigDecimal amount;

    @Column(precision = 65, scale = 6)
    private BigDecimal credit;

    @Column(precision = 65, scale = 6)
    private BigDecimal debit;

    @Column(name = "principal_repaid_derived", nullable = false, precision = 65, scale = 6)
    private BigDecimal principalRepaidDerived;

    @Column(name = "interest_repaid_derived", nullable = false, precision = 65, scale = 6)
    private BigDecimal interestRepaidDerived;

    @Column(name = "fees_repaid_derived", nullable = false, precision = 65, scale = 6)
    private BigDecimal feesRepaidDerived;

    @Column(name = "penalties_repaid_derived", nullable = false, precision = 65, scale = 6)
    private BigDecimal penaltiesRepaidDerived;

    @Column(name = "loan_transaction_type_id", nullable = false)
    private Long loanTransactionTypeId;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean reversed = false;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean reversible = false;

    @Column(name = "submitted_on")
    private LocalDate submittedOn;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "created_on")
    private LocalDate createdOn;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('pending','approved','declined')")
    private TransactionStatus status;

    private String reference;

    @Column(name = "gateway_id")
    private String gatewayId;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "payment_gateway_data", columnDefinition = "TEXT")
    private String paymentGatewayData;

    @Column(name = "online_transaction", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean onlineTransaction = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum TransactionStatus {
        pending,
        approved,
        declined
    }
}
