package com.project.ecommerce.service;

import com.project.ecommerce.dto.user.UserSignup;
import com.project.ecommerce.entity.user.UserDetailsEntity;
import com.project.ecommerce.entity.user.UserEntity;
import com.project.ecommerce.repository.RoleRepository;
import com.project.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    public void registerTheUser(UserSignup theUser){
        var userEntity = new UserEntity();
        userEntity.setPassword(encoder.encode(theUser.getPassword()));
        userEntity.setUsername(theUser.getUsername());

        var userDetailsEntity = new UserDetailsEntity();
        userDetailsEntity.setAge(theUser.getAge());
        userDetailsEntity.setEmail(theUser.getEmail());
        userDetailsEntity.setPhoneNo(theUser.getPhoneNo());

        userEntity.setUserDetailsEntity(userDetailsEntity);
        userEntity.setIsEnabled(true);

        var rolesEntity = roleRepository.getOne("ROLE_USER");

        userEntity.addRole(rolesEntity);

        userRepository.save(userEntity);
    }

    public boolean userExist(String username){
        return userRepository.existsById(username);
    }
}
