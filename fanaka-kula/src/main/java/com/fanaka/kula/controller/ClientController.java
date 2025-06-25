package com.fanaka.kula.controller;

import com.fanaka.kula.config.response.Response;
import org.springframework.http.ResponseEntity;

public interface ClientController {
    ResponseEntity<Response> clientExitsByPhone(String phoneNumber);
    ResponseEntity<Response> clientExitsByNrc(String nrcNumber);
}
