package com.fanaka.kula.service;

import com.fanaka.kula.models.UserCreationDto;
import com.fanaka.kula.models.UserDto;

public interface UserService {
    UserDto createUser(UserCreationDto userCreationDto);
    UserDto getUserById(Long id);
    UserDto getUserByEmail(String email);
    UserDto getUserByPhone(String phone);
    UserDto getUserByUsername(String username);
    Boolean userExistsByPhoneNumber(String phoneNumber);
}
