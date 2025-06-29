package com.fanaka.kula.controller;

import com.fanaka.kula.config.response.Response;
import com.fanaka.kula.models.UserCreationDto;
import com.fanaka.kula.models.UserLoginDto;
import org.springframework.http.ResponseEntity;

public interface AuthController {
    ResponseEntity<Object> createUserEntity(UserCreationDto userCreationDto);
    ResponseEntity<Object> login(UserLoginDto userLoginDto);
}
