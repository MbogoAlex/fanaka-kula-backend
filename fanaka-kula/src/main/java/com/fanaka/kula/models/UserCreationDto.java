package com.fanaka.kula.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreationDto {
    private String phoneNumber;
//    private String firstName;
//    private String lastName;
    private String username;
    private String email;
//    private String email;
    private String password;
}
