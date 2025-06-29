package com.fanaka.kula.service;

import com.fanaka.kula.dao.ClientDao;
import com.fanaka.kula.models.Client;
import com.fanaka.kula.models.ExistsDto;
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
    public ExistsDto clientExitsByPhone(String phoneNumber) {
        ExistsDto existsDto = new ExistsDto();
        try {
            Client client = clientDao.getClientByPhone(phoneNumber);
            existsDto.setIsExists(client != null);
            return existsDto;
        } catch (Exception e) {
            existsDto.setIsExists(false);
            return existsDto;
        }
    }

    @Override
    public ExistsDto clientExitsByNrc(String nrcNumber) {
        ExistsDto existsDto = new ExistsDto();
        System.out.println("NRC::" + nrcNumber);
        try {
            Client client = clientDao.getClientByNrcNumber(nrcNumber.replaceFirst("/", ""));
            existsDto.setIsExists(client != null);
            return existsDto;
        } catch (Exception e) {
            existsDto.setIsExists(false);
            return existsDto;
        }
    }
}
