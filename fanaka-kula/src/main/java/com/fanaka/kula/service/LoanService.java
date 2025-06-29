package com.fanaka.kula.service;

import com.fanaka.kula.models.Loan;
import com.fanaka.kula.models.LoanDto;
import com.fanaka.kula.models.PagedResult;

import java.time.LocalDateTime;
import java.util.List;

public interface LoanService {
    PagedResult<LoanDto> getClientLoans(Long userId, String clientType, String repaymentFrequencyType, String interestRateType, String interestMethodology, String repaymentSchedule, String status, String onboardingStage, String createdAtSort, LocalDateTime startDate, LocalDateTime endDate, Integer currentPage, Integer size);
    LoanDto getLoan(Long loanId);
}
