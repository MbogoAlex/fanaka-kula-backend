package com.fanaka.kula.service;

import com.fanaka.kula.models.ExistsDto;

public interface ClientService {
    ExistsDto clientExitsByPhone(String phoneNumber);
    ExistsDto clientExitsByNrc(String nrcNumber);
}
