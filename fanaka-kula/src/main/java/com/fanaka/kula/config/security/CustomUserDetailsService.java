package com.fanaka.kula.config.security;

import com.fanaka.kula.dao.UserEntityDao;
import com.fanaka.kula.models.Role;
import com.fanaka.kula.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserEntityDao userEntityDao;

    @Autowired
    public CustomUserDetailsService(UserEntityDao userEntityDao) {
        this.userEntityDao = userEntityDao;
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        // 1) Load the user by phone number
        UserEntity user = userEntityDao.getUserEntityByPhone(phone);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with phone number: " + phone);
        }

        // 2) Map Role entities to GrantedAuthority
        Collection<GrantedAuthority> authorities = mapRolesToAuthorities(user.getRoles());

        // 3) Return Spring Security User (username, password, authorities)
        return new org.springframework.security.core.userdetails.User(
                user.getPhone(),        // principal
                user.getPassword(),     // credentials
                authorities             // roles
        );
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                // Use the 'name' column of your Role table as the authority string
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
