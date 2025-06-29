package com.fanaka.kula.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;

public interface LoanController {
    ResponseEntity<Object> getClientLoans(@AuthenticationPrincipal User user);
    ResponseEntity<Object> getLoan(Long loanId);
}
