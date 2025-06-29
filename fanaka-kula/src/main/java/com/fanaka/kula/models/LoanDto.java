package com.fanaka.kula.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanDto {
    private Long id;
    private Long clientId;
    private String accountNumber;
    private BigDecimal principal;
    private BigDecimal approvedAmount;
    private BigDecimal interestRate;
    private Integer loanTerm;
    private Integer repaymentFrequency;
    private String repaymentFrequencyType;
    private Loan.LoanStatus status;
    private LocalDate submittedOnDate;
    private LocalDate disbursedOnDate;
    private LocalDate expectedFirstPaymentDate;
    private LocalDate expectedMaturityDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Notes fields
    private String approvedNotes;
    private String disbursedNotes;
    private String rejectedNotes;
    private String writtenOffNotes;
    private String closedNotes;
    private String rescheduledNotes;
    private String withdrawnNotes;
    private String putOnHoldNotes;
}
