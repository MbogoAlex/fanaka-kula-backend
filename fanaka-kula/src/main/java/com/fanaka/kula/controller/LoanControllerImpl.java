package com.fanaka.kula.controller;

import com.fanaka.kula.config.response.BuildResponse;
import com.fanaka.kula.models.UserDto;
import com.fanaka.kula.service.LoanService;
import com.fanaka.kula.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/")
public class LoanControllerImpl implements LoanController{

    private final BuildResponse buildResponse;
    private final LoanService loanService;
    private final UserService userService;

    @Autowired
    public LoanControllerImpl(
            LoanService loanService,
            BuildResponse buildResponse,
            UserService userService
    ) {
        this.loanService = loanService;
        this.buildResponse = buildResponse;
        this.userService = userService;

    }

    @GetMapping("loan")
    @Override
    public ResponseEntity<Object> getClientLoans(
            @AuthenticationPrincipal User user,
            @RequestParam(name = "clientType", required = false) String clientType,
            @RequestParam(name = "repaymentFrequencyType", required = false) String repaymentFrequencyType,
            @RequestParam(name = "interestRateType", required = false) String interestRateType,
            @RequestParam(name = "interestMethodology", required = false) String interestMethodology,
            @RequestParam(name = "repaymentSchedule", required = false) String repaymentSchedule,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "onboardingStage", required = false) String onboardingStage,
            @RequestParam(name = "createdAtSort", required = false) String createdAtSort,
            @RequestParam(name = "startDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(name = "endDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(name = "currentPage", required = false) Integer currentPage,
            @RequestParam(name = "size") Integer size
            ) {

        LocalDateTime formattedStartDateTime = startDate != null ? startDate.atStartOfDay() : null;
        LocalDateTime formattedEndDateTime = endDate != null ? endDate.atStartOfDay() : null;

        UserDto userDto = userService.getUserByPhone(user.getUsername());
        return buildResponse.success(loanService.getClientLoans(userDto.getId(), clientType, repaymentFrequencyType, interestRateType, interestMethodology, repaymentSchedule, status, onboardingStage, createdAtSort, formattedStartDateTime, formattedEndDateTime, currentPage, size),"User loans fetched",  null, HttpStatus.OK);
    }

    @GetMapping("loan/id/{loanId}")
    @Override
    public ResponseEntity<Object> getLoan(@PathVariable("loanId") Long loanId) {
        return buildResponse.success(loanService.getLoan(loanId),   "loan fetched", null, HttpStatus.OK);
    }
}
