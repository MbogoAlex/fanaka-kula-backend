package com.fanaka.kula.service;

import com.fanaka.kula.dao.ClientDao;
import com.fanaka.kula.dao.RoleDao;
import com.fanaka.kula.dao.UserEntityDao;
import com.fanaka.kula.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserEntityDao userEntityDao;
    private final UserMapper userMapper;
    private final RoleDao roleDao;
    private final ClientDao clientDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(
            UserEntityDao userEntityDao,
            UserMapper userMapper,
            RoleDao roleDao,
            ClientDao clientDao,
            PasswordEncoder passwordEncoder
    ) {
        this.userEntityDao = userEntityDao;
        this.userMapper = userMapper;
        this.roleDao = roleDao;
        this.clientDao = clientDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public UserDto createUser(UserCreationDto userCreationDto) {
        Role role = roleDao.getRoleByName("client");
        Client client = clientDao.getClientByPhone(userCreationDto.getPhoneNumber());

        String fName = client.getFirstName();
        String lName = client.getLastName();
        String name = fName + " " + lName;
        String email = client.getEmail();

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        UserEntity userEntity = UserEntity.builder()
                .phone(userCreationDto.getPhoneNumber())
                .firstName(fName)
                .lastName(lName)
                .name(name)
                .username(userCreationDto.getUsername())
                .email(email)
                .password(passwordEncoder.encode(userCreationDto.getPin()))
                .roles(roleSet)
                .enableGoogle2fa(false)
                .name(userCreationDto.getUsername())
                .createdAt(LocalDateTime.now())
                .createdById(client.getCreatedById())
                .address(client.getAddress())
                .city(client.getCity())
                .gender(String.valueOf(client.getGender()))
                .regionId(client.getRegionId())
                .districtId(client.getDistrictId())
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

    @Override
    public ExistsDto userExistsByPhoneNumber(String phoneNumber) {
        ExistsDto existsDto = new ExistsDto();
        try {
            UserEntity userEntity = userEntityDao.getUserEntityByPhone(phoneNumber);
            existsDto.setIsExists(userEntity != null);
            return existsDto;
        } catch (Exception e) {
            existsDto.setIsExists(false);
            return existsDto;
        }
    }
}
