package com.fanaka.kula.controller;

import com.fanaka.kula.config.response.BuildResponse;
import com.fanaka.kula.config.response.Response;
import com.fanaka.kula.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class ClientControllerImpl implements ClientController{

    private final ClientService clientService;
    private final BuildResponse buildResponse;

    @Autowired
    public ClientControllerImpl(
            ClientService clientService,
            BuildResponse buildResponse
    ) {
        this.clientService = clientService;
        this.buildResponse = buildResponse;
    }

    @GetMapping("client-exists/phone/{phone}")
    @Override
    public ResponseEntity<Response> clientExitsByPhone(@PathVariable("phone") String phoneNumber) {
        return buildResponse.createResponse("client-exists", clientService.clientExitsByPhone(phoneNumber), "client check", HttpStatus.OK);
    }

    @GetMapping("client-exists/nrc/{nrc}")
    @Override
    public ResponseEntity<Response> clientExitsByNrc(@PathVariable("nrc") String nrcNumber) {
        return buildResponse.createResponse("client-exists", clientService.clientExitsByNrc(nrcNumber), "client check", HttpStatus.OK);
    }
}
