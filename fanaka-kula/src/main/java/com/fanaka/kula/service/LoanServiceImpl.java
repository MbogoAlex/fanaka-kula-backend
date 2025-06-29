package com.fanaka.kula.service;

import com.fanaka.kula.dao.ClientDao;
import com.fanaka.kula.dao.LoanDao;
import com.fanaka.kula.dao.UserEntityDao;
import com.fanaka.kula.models.Loan;
import com.fanaka.kula.models.LoanDto;
import com.fanaka.kula.models.LoanMapper;
import com.fanaka.kula.models.PagedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    public PagedResult<LoanDto> getClientLoans(Long userId, String clientType, String repaymentFrequencyType, String interestRateType, String interestMethodology, String repaymentSchedule, String status, String onboardingStage, String createdAtSort, LocalDateTime startDate, LocalDateTime endDate, Integer currentPage, Integer size) {
        String phone = userEntityDao.findPhoneById(userId);
        Long clientId = clientDao.getClientByPhone(phone).getId();

        PagedResult<Loan> pagedLoans = loanDao.getLoansByClientID(clientId, clientType, repaymentFrequencyType, interestRateType, interestMethodology, repaymentSchedule, status, onboardingStage, createdAtSort, startDate, endDate, currentPage, size);
        List<LoanDto> loansDto = pagedLoans.getData().stream().map(loanMapper::toLoanDto).toList();

        PagedResult<LoanDto> pagedResult = new PagedResult<>();
        pagedResult.setData(loansDto);
        pagedResult.setTotal(pagedLoans.getTotal());
        pagedResult.setCurrentPage(pagedLoans.getCurrentPage());
        pagedResult.setLastPage(pagedLoans.getLastPage());
        pagedResult.setTotalPerPage(pagedLoans.getTotalPerPage());

        return pagedResult;
    }

    @Override
    public LoanDto getLoan(Long loanId) {
        return loanMapper.toLoanDto(loanDao.getLoanByID(loanId));
    }
}
