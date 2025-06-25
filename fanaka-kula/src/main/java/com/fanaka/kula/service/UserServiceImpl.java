package com.fanaka.kula.service;

import com.fanaka.kula.dao.RoleDao;
import com.fanaka.kula.dao.UserEntityDao;
import com.fanaka.kula.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserEntityDao userEntityDao;
    private final UserMapper userMapper;
    private final RoleDao roleDao;

    @Autowired
    public UserServiceImpl(
            UserEntityDao userEntityDao,
            UserMapper userMapper,
            RoleDao roleDao
    ) {
        this.userEntityDao = userEntityDao;
        this.userMapper = userMapper;
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public UserDto createUser(UserCreationDto userCreationDto) {
        Role role = roleDao.getRoleByName("client");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        UserEntity userEntity = UserEntity.builder()
                .phone(userCreationDto.getPhoneNumber())
                .username(userCreationDto.getUsername())
                .password(userCreationDto.getPassword())
                .roles(roleSet)
                .build();

        return userMapper.userToUserDto(userEntityDao.createUserEntity(userEntity));
    }

    @Override
    public UserDto getUserById(Long id) {
        return userMapper.userToUserDto(userEntityDao.getUserEntityById(id));
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return userMapper.userToUserDto(userEntityDao.getUserEntityByEmail(email));
    }

    @Override
    public UserDto getUserByPhone(String phone) {
        return userMapper.userToUserDto(userEntityDao.getUserEntityByPhone(phone));
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return userMapper.userToUserDto(userEntityDao.getUserEntityByUsername(username));
    }
}
