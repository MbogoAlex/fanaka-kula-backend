package com.fanaka.kula.controller;

import com.fanaka.kula.config.response.BuildResponse;
import com.fanaka.kula.config.response.Response;
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
    public ResponseEntity<Response> getClientLoans(
            @AuthenticationPrincipal User user
    ) {
        UserDto userDto = userService.getUserByPhone(user.getUsername());
        return buildResponse.createResponse("loans", loanService.getClientLoans(userDto.getId()), "User loans fetched", HttpStatus.OK);
    }

    @GetMapping("loan/id/{loanId}")
    @Override
    public ResponseEntity<Response> getLoan(@PathVariable("loanId") Long loanId) {
        return buildResponse.createResponse("loan", loanService.getLoan(loanId), "loan fetched", HttpStatus.OK);
    }
}
