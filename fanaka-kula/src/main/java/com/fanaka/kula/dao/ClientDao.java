package com.fanaka.kula.dao;

import com.fanaka.kula.models.Client;

import java.util.List;

public interface ClientDao {
    Client getClientByID(Long id);
    Client getClientByUsername(String username);
    Client getClientByEmail(String email);
    Client getClientByPhone(String phone);
    Client getClientByNrcNumber(String nrcNumber);
    List<Client> getClients();
}
