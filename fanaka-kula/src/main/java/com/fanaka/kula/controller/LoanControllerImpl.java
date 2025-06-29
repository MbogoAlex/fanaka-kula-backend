package com.fanaka.kula.controller;

import com.fanaka.kula.config.response.BuildResponse;
import com.fanaka.kula.models.UserDto;
import com.fanaka.kula.service.LoanService;
import com.fanaka.kula.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("loan/user")
    @Override
    public ResponseEntity<Object> getClientLoans(
            @AuthenticationPrincipal User user
    ) {
        UserDto userDto = userService.getUserByPhone(user.getUsername());
        return buildResponse.success(loanService.getClientLoans(userDto.getId()),"User loans fetched",  null, HttpStatus.OK);
    }

    @GetMapping("loan/id/{loanId}")
    @Override
    public ResponseEntity<Object> getLoan(@PathVariable("loanId") Long loanId) {
        return buildResponse.success(loanService.getLoan(loanId),   "loan fetched", null, HttpStatus.OK);
    }
}
