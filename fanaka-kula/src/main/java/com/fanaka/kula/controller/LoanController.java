package com.fanaka.kula.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface LoanController {
    ResponseEntity<Object> getClientLoans(@AuthenticationPrincipal User user, String clientType, String repaymentFrequencyType, String interestRateType, String interestMethodology, String repaymentSchedule, String status, String onboardingStage, String createdAtSort, LocalDate startDate, LocalDate endDate, Integer currentPage, Integer size);
    ResponseEntity<Object> getLoan(Long loanId);
}
