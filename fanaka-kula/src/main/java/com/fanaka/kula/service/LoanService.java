package com.fanaka.kula.service;

import com.fanaka.kula.models.Loan;
import com.fanaka.kula.models.LoanDto;

import java.util.List;

public interface LoanService {
    List<LoanDto> getClientLoans(Long userId);
    LoanDto getLoan(Long loanId);
}
