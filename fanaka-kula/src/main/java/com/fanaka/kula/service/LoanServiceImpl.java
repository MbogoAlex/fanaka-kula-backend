package com.fanaka.kula.service;

import com.fanaka.kula.dao.ClientDao;
import com.fanaka.kula.dao.LoanDao;
import com.fanaka.kula.dao.UserEntityDao;
import com.fanaka.kula.models.LoanDto;
import com.fanaka.kula.models.LoanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService{
    private final LoanDao loanDao;
    private final UserEntityDao userEntityDao;
    private final ClientDao clientDao;
    private final LoanMapper loanMapper;

    @Autowired
    public LoanServiceImpl(
            LoanDao loanDao,
            UserEntityDao userEntityDao,
            ClientDao clientDao,
            LoanMapper loanMapper
    ) {
        this.loanDao = loanDao;
        this.userEntityDao = userEntityDao;
        this.clientDao = clientDao;
        this.loanMapper = loanMapper;
    }

    @Override
    @Transactional
    public List<LoanDto> getClientLoans(Long userId) {
        String phone = userEntityDao.findPhoneById(userId);
        Long clientId = clientDao.getClientByPhone(phone).getId();
        return loanDao.getLoansByClientID(clientId).stream().map(loanMapper::toLoanDto).collect(Collectors.toList());
    }

    @Override
    public LoanDto getLoan(Long loanId) {
        return loanMapper.toLoanDto(loanDao.getLoanByID(loanId));
    }
}
