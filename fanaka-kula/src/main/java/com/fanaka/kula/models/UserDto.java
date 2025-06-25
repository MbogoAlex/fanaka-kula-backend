package com.fanaka.kula.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phone;
    private LocalDateTime emailVerifiedAt;
    private LocalDateTime lastLogin;
    private String address;
    private String city;
    private String gender;
    private String photo;
}
