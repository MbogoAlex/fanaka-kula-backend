package com.fanaka.kula.controller;

import com.fanaka.kula.config.response.Response;
import org.springframework.http.ResponseEntity;

public interface ClientController {
    ResponseEntity<Object> clientExitsByPhone(String phoneNumber);
    ResponseEntity<Object> clientExitsByNrc(String nrcNumber);
}
