package com.fanaka.kula.service;

import com.fanaka.kula.dao.LoanDao;
import com.fanaka.kula.models.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService{
    private final LoanDao loanDao;

    @Autowired
    public LoanServiceImpl(LoanDao loanDao) {
        this.loanDao = loanDao;
    }

    @Override
    public List<Loan> getClientLoans(Long clientId) {
        return loanDao.getLoansByClientID(clientId);
    }

    @Override
    public Loan getLoan(Long loanId) {
        return loanDao.getLoanByID(loanId);
    }
}
