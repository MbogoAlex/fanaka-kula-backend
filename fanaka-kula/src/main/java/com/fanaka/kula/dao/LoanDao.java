package com.fanaka.kula.dao;

import com.fanaka.kula.models.Loan;
import com.fanaka.kula.models.PagedResult;

import java.time.LocalDateTime;
import java.util.List;

public interface LoanDao {
    Loan getLoanByID(Long id);

    PagedResult<Loan> getLoansByClientID(Long clientId, String clientType, String repaymentFrequencyType, String interestRateType, String interestMethodology, String repaymentSchedule, String status, String onboardingStage, String createdAtSort, LocalDateTime startDate, LocalDateTime endDate, Integer currentPage, Integer size);
}
