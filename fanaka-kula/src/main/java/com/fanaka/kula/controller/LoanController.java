package com.fanaka.kula.controller;

import com.fanaka.kula.config.response.Response;
import com.fanaka.kula.models.Loan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface LoanController {
    ResponseEntity<Object> getClientLoans(@AuthenticationPrincipal User user);
    ResponseEntity<Object> getLoan(Long loanId);
}
