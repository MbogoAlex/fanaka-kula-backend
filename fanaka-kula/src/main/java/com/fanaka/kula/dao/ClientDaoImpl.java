package com.fanaka.kula.dao;

import com.fanaka.kula.models.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientDaoImpl implements ClientDao{
    private final EntityManager entityManager;

    @Autowired
    public ClientDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Client getClientByID(Long id) {
        TypedQuery<Client> query = entityManager.createQuery("from Client where id = :id", Client.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Client getClientByUsername(String username) {
//        TypedQuery<Client> query = entityManager.createQuery("from Client where username = :username", Client.class);
        return null;
    }

    @Override
    public Client getClientByEmail(String email) {
        TypedQuery<Client> query = entityManager.createQuery("from Client where email = :email", Client.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

    @Override
    public Client getClientByPhone(String phone) {
        TypedQuery<Client> query = entityManager.createQuery("from Client where phone = :phone", Client.class);
        query.setParameter("phone", phone);
        return query.getSingleResult();
    }

    @Override
    public Client getClientByNrcNumber(String nrcNumber) {
        TypedQuery<Client> query = entityManager.createQuery("from Client where nrcNumber = :nrcNumber", Client.class);
        query.setParameter("nrcNumber", nrcNumber);
        return query.getSingleResult();
    }

    @Override
    public List<Client> getClients() {
        TypedQuery<Client> query = entityManager.createQuery("from Client", Client.class);
        return query.getResultList();
    }
}
