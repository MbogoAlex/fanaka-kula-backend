package com.fanaka.kula.service;

import com.fanaka.kula.dao.ClientDao;
import com.fanaka.kula.dao.LoanDao;
import com.fanaka.kula.dao.UserEntityDao;
import com.fanaka.kula.models.Loan;
import com.fanaka.kula.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService{
    private final LoanDao loanDao;
    private final UserEntityDao userEntityDao;
    private final ClientDao clientDao;

    @Autowired
    public LoanServiceImpl(
            LoanDao loanDao,
            UserEntityDao userEntityDao,
            ClientDao clientDao
    ) {
        this.loanDao = loanDao;
        this.userEntityDao = userEntityDao;
        this.clientDao = clientDao;
    }

    @Override
    @Transactional
    public List<Loan> getClientLoans(Long userId) {
        UserEntity userEntity = userEntityDao.getUserEntityById(userId);

        Long clientId = clientDao.getClientByPhone(userEntity.getPhone()).getId();

        return loanDao.getLoansByClientID(clientId);
    }

    @Override
    public Loan getLoan(Long loanId) {
        return loanDao.getLoanByID(loanId);
    }
}
