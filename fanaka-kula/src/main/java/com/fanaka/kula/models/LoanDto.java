package com.fanaka.kula.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanDto {
    private Long id;
    private Long clientId;
    private Long createdById;
    private Loan.ClientType clientType;
    private Long groupId;
    private Long branchId;
    private Long currencyId;
    private Long loanProductId;
    private LocalDate approvedOnDate;
    private Long approvedByUserId;
    private String approvedNotes;
    private LocalDate expectedDisbursementDate;
    private LocalDate expectedFirstPaymentDate;
    private LocalDate firstPaymentDate;
}
