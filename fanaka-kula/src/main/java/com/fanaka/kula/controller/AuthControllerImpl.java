package com.fanaka.kula.controller;

import com.fanaka.kula.config.response.BuildResponse;
import com.fanaka.kula.config.response.Response;
import com.fanaka.kula.config.security.JWTGenerator;
import com.fanaka.kula.dao.UserEntityDao;
import com.fanaka.kula.models.UserCreationDto;
import com.fanaka.kula.models.UserEntity;
import com.fanaka.kula.models.UserLoginDto;
import com.fanaka.kula.models.UserMapper;
import com.fanaka.kula.service.ClientService;
import com.fanaka.kula.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/")
public class AuthControllerImpl implements AuthController{

    private final BuildResponse buildResponse;
    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;
    private final UserService userService;
    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;
    private final UserEntityDao userEntityDao;
    private final UserMapper userMapper;

    @Autowired
    public AuthControllerImpl(
            BuildResponse buildResponse,
            AuthenticationManager authenticationManager,
            JWTGenerator jwtGenerator,
            UserService userService,
            ClientService clientService,
            PasswordEncoder passwordEncoder,
            UserEntityDao userEntityDao,
            UserMapper userMapper
    ) {
        this.buildResponse = buildResponse;
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
        this.userService = userService;
        this.clientService = clientService;
        this.passwordEncoder = passwordEncoder;
        this.userEntityDao = userEntityDao;
        this.userMapper = userMapper;
    }

    @PostMapping("user-account")
    @Override
    public ResponseEntity<Response> createUserEntity(@RequestBody UserCreationDto userCreationDto) {
        Boolean exists = clientService.clientExitsByPhone(userCreationDto.getPhoneNumber());

        if(exists) {
            return buildResponse.createResponse("user-account", userService.createUser(userCreationDto), "User account created", HttpStatus.CREATED);
        }

        return buildResponse.createResponse("user-account", null, "Client with this phone number does not exist. The client needs to be onboarded by the FE first", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("login")
    public ResponseEntity<Response> login(
            @RequestBody UserLoginDto userLoginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLoginDto.getPhoneNumber(),
                            userLoginDto.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);

            UserEntity userEntity =
                    userEntityDao.getUserEntityByPhone(userLoginDto.getPhoneNumber());

            Map<Object,Object> data = new HashMap<>();
            data.put("user", userMapper.userToUserDto(userEntity));
            data.put("token", token);

            return buildResponse.createResponse(
                    "user-login",
                    data,
                    "User logged in",
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return buildResponse.createResponse(
                    null, null,
                    "Invalid credentials",
                    HttpStatus.UNAUTHORIZED
            );
        }
    }

}
