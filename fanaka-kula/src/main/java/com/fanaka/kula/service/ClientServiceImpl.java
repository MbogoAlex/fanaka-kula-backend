package com.fanaka.kula.service;

import com.fanaka.kula.dao.ClientDao;
import com.fanaka.kula.models.Client;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientDao clientDao;

    @Autowired
    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public Boolean clientExitsByPhone(String phoneNumber) {
        try {
            Client client = clientDao.getClientByPhone(phoneNumber);
            return client != null;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public Boolean clientExitsByNrc(String nrcNumber) {
        try {
            Client client = clientDao.getClientByNrcNumber(nrcNumber);
            return client != null;
        } catch (NoResultException e) {
            return false;
        }
    }
}
