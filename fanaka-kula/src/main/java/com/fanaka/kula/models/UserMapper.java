package com.fanaka.kula.models;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserMapper {
    public UserDto userToUserDto(UserEntity userEntity) {
        List<String> roles = new ArrayList<>();

        if (userEntity.getRoles() != null && !userEntity.getRoles().isEmpty()) {
            userEntity.getRoles().forEach(role -> {roles.add(role.getName());});
        }

        UserDto userDto = UserDto.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .phone(userEntity.getPhone())
                .emailVerifiedAt(userEntity.getEmailVerifiedAt())
                .lastLogin(userEntity.getLastLogin())
                .address(userEntity.getAddress())
                .city(userEntity.getCity())
                .gender(userEntity.getGender())
                .photo(userEntity.getPhoto())
                .roles(roles)
                .build();

        return userDto;
    }
}
