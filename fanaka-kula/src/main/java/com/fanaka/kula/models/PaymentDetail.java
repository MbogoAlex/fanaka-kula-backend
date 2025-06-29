package com.fanaka.kula.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_by_id")
    private Integer createdById;

//    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "payment_type_id")
    private Integer paymentTypeId;

    @Column(name = "transaction_type", length = 191)
    private String transactionType;

    private Integer reference;

    @Column(name = "cheque_number", length = 191)
    private String chequeNumber;

    @Column(length = 191)
    private String receipt;

    @Column(name = "account_number", length = 191)
    private String accountNumber;

    @Column(name = "bank_name", length = 191)
    private String bankName;

    @Column(name = "routing_code", length = 191)
    private String routingCode;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
