package com.fanaka.kula.models;

import org.springframework.stereotype.Component;

@Component
public class LoanMapper {
    public LoanDto toLoanDto(Loan loan) {
        LoanDto loanDto = LoanDto.builder()
                .id(loan.getId())
                .clientId(loan.getClient() != null ? loan.getClient().getId() : null)
                .accountNumber(loan.getAccountNumber())
                .principal(loan.getPrincipal())
                .approvedAmount(loan.getApprovedAmount())
                .interestRate(loan.getInterestRate())
                .loanTerm(loan.getLoanTerm())
                .repaymentFrequency(loan.getRepaymentFrequency())
                .repaymentFrequencyType(loan.getRepaymentFrequencyType().name())
                .status(loan.getStatus())
                .submittedOnDate(loan.getSubmittedOnDate())
                .disbursedOnDate(loan.getDisbursedOnDate())
                .expectedFirstPaymentDate(loan.getExpectedFirstPaymentDate())
                .expectedMaturityDate(loan.getExpectedMaturityDate())
                .createdAt(loan.getCreatedAt())
                .updatedAt(loan.getUpdatedAt())
                // map all notes
                .approvedNotes(loan.getApprovedNotes())
                .disbursedNotes(loan.getDisbursedNotes())
                .rejectedNotes(loan.getRejectedNotes())
                .writtenOffNotes(loan.getWrittenOffNotes())
                .closedNotes(loan.getClosedNotes())
                .rescheduledNotes(loan.getRescheduledNotes())
                .withdrawnNotes(loan.getWithdrawnNotes())
                .putOnHoldNotes(loan.getPutOnHoldNotes())
                .build();

        return loanDto;
    }
}
