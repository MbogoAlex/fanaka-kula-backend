package com.fanaka.kula.dao;

import com.fanaka.kula.models.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserEntityDaoImpl implements UserEntityDao{

    private final EntityManager entityManager;

    @Autowired
    public UserEntityDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public UserEntity createUserEntity(UserEntity userEntity) {
        entityManager.persist(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity getUserEntityById(Long id) {
        TypedQuery<UserEntity> query = entityManager.createQuery("from UserEntity where id = :id", UserEntity.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public UserEntity getUserEntityByEmail(String email) {
        TypedQuery<UserEntity> query = entityManager.createQuery("from UserEntity where email = :email", UserEntity.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

    @Override
    public UserEntity getUserEntityByPhone(String phone) {
        TypedQuery<UserEntity> query = entityManager.createQuery("from UserEntity where phone = :phone", UserEntity.class);
        query.setParameter("phone", phone);
        return query.getSingleResult();
    }

    @Override
    public UserEntity getUserEntityByUsername(String username) {
        TypedQuery<UserEntity> query = entityManager.createQuery("from UserEntity where username = :username", UserEntity.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    @Override
    public UserEntity getUserEntityByUsernameAndPassword(String username, String password) {

        return null;
    }

    @Override
    public UserEntity getUserEntityByUsernameAndEmail(String username, String email) {
        return null;
    }

    @Override
    public UserEntity getUserEntityByEmailAndUsername(String email, String username) {
        return null;
    }

    @Override
    public List<UserEntity> getUsers() {
        TypedQuery<UserEntity> query = entityManager.createQuery("from UserEntity", UserEntity.class);
        return query.getResultList();
    }
}
