package com.fanaka.kula.controller;

import com.fanaka.kula.config.response.Response;
import com.fanaka.kula.models.UserCreationDto;
import com.fanaka.kula.models.UserLoginDto;
import org.springframework.http.ResponseEntity;

public interface AuthController {
    ResponseEntity<Response> createUserEntity(UserCreationDto userCreationDto);
    ResponseEntity<Response> login(UserLoginDto userLoginDto);
}
