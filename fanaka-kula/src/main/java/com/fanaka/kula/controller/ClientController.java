package com.fanaka.kula.controller;

import org.springframework.http.ResponseEntity;

public interface ClientController {
    ResponseEntity<Object> clientExitsByPhone(String phoneNumber);
    ResponseEntity<Object> clientExitsByNrc(String nrcNumber);
}
