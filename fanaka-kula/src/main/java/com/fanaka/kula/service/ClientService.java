package com.fanaka.kula.service;

public interface ClientService {
    Boolean clientExitsByPhone(String phoneNumber);
    Boolean clientExitsByNrc(String nrcNumber);
}
