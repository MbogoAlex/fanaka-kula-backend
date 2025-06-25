package com.fanaka.kula.dao;

import com.fanaka.kula.models.UserEntity;

import java.util.List;

public interface UserEntityDao {
    UserEntity createUserEntity(UserEntity userEntity);
    UserEntity getUserEntityById(Long id);
    UserEntity getUserEntityByEmail(String email);
    UserEntity getUserEntityByPhone(String phone);
    UserEntity getUserEntityByUsername(String username);
    UserEntity getUserEntityByUsernameAndPassword(String username, String password);
    UserEntity getUserEntityByUsernameAndEmail(String username, String email);
    UserEntity getUserEntityByEmailAndUsername(String email, String username);
    List<UserEntity> getUsers();
}
