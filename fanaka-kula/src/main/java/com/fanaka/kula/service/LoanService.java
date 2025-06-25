package com.fanaka.kula.service;

import com.fanaka.kula.models.Loan;

import java.util.List;

public interface LoanService {
    List<Loan> getClientLoans(Long userId);
    Loan getLoan(Long loanId);
}
