package com.fanaka.kula.controller;

import com.fanaka.kula.config.response.BuildResponse;
import com.fanaka.kula.config.response.Response;
import com.fanaka.kula.config.security.JWTGenerator;
import com.fanaka.kula.dao.UserEntityDao;
import com.fanaka.kula.models.*;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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
    public ResponseEntity<Object> createUserEntity(@RequestBody UserCreationDto userCreationDto) {
        ExistsDto userExists = userService.userExistsByPhoneNumber(userCreationDto.getPhoneNumber());
        ExistsDto clientExists = clientService.clientExitsByPhone(userCreationDto.getPhoneNumber());

        System.out.println("userExists : " + userExists);
        System.out.println("clientExists : " + clientExists);
        System.out.println("userCreationDto : " + userCreationDto);

        if(userExists.getIsExists()) {
            Map<String, Object> errors = new HashMap<>();
            errors.put("error", "User account for this client already created");
            return buildResponse.error("Account creation failed", errors, HttpStatus.CONFLICT);
        }

        if(clientExists.getIsExists()) {
            return buildResponse.success(userService.createUser(userCreationDto),  "User account created", null, HttpStatus.CREATED);
        }

        Map<String, Object> errors = new HashMap<>();
        errors.put("error", "Client with this phone number does not exist. The client needs to be onboarded by the FE first");
        return buildResponse.error("Account creation failed", errors, HttpStatus.NOT_FOUND);
    }

    @PostMapping("login")
    @Transactional
    public ResponseEntity<Object> login(
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
            userEntity.setLastLogin(LocalDateTime.now());
            userEntityDao.updateUserEntity(userEntity);


            Map<Object,Object> data = new HashMap<>();
            data.put("user", userMapper.userToUserDto(userEntity));
            data.put("token", token);

            return buildResponse.success(
                    data,
                    "User logged in",
                    null,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            Map<String, Object> errors = new HashMap<>();
            errors.put("error", "Invalid credentials");
            return buildResponse.error(
                    "Login failed",
                    errors,
                    HttpStatus.UNAUTHORIZED
            );
        }
    }

}
