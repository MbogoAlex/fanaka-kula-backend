package com.fanaka.kula.dao;

import com.fanaka.kula.models.Loan;

import java.util.List;

public interface LoanDao {
    Loan getLoanByID(Long id);

    List<Loan> getLoansByClientID(Long clientId);
}
