package com.fanaka.kula.dao;

import com.fanaka.kula.models.Loan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoanDaoImpl implements LoanDao{

    private final EntityManager entityManager;

    @Autowired
    public LoanDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Loan getLoanByID(Long id) {
        TypedQuery<Loan> query = entityManager.createQuery("from Loan where id = :id", Loan.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }


    @Override
    public List<Loan> getLoansByClientID(Long clientId) {
        TypedQuery<Loan> query = entityManager.createQuery("from Loan where client.id = :clientId", Loan.class);
        query.setParameter("clientId", clientId);
        return query.getResultList();
    }
}
