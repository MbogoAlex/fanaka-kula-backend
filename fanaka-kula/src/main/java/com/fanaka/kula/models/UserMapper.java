package com.fanaka.kula.models;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto userToUserDto(UserEntity userEntity) {
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
                .build();

        return userDto;
    }
}
