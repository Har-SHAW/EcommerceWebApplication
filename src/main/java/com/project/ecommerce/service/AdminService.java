package com.project.ecommerce.service;

import com.project.ecommerce.dto.user.AdminUser;
import com.project.ecommerce.dto.user.UserRole;
import com.project.ecommerce.entity.user.UserEntity;
import com.project.ecommerce.repository.RoleRepository;
import com.project.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public List<AdminUser> getAllUsers(){
        List<UserEntity> userEntities = userRepository.findAll();

        List<AdminUser> adminUsers = new ArrayList<>();
        for (UserEntity userEntity : userEntities){
            adminUsers.add(new AdminUser(userEntity));
        }

        return adminUsers;
    }

    public void changeRole(UserRole userRole){
        var rolesEntity = roleRepository.getOne(userRole.getRole());
        var userEntity = userRepository.getOne(userRole.getUsername());
        if (userRole.getAction().equals("Add")){
            userEntity.addRole(rolesEntity);
        }else{
            userEntity.getRolesEntities().remove(rolesEntity);
        }
        userRepository.save(userEntity);
    }

    public void disableUser(String username){
        var userEntity = userRepository.getOne(username);
        userEntity.setIsEnabled(false);
        userRepository.save(userEntity);
    }

    public void enableUser(String username){
        var userEntity = userRepository.getOne(username);
        userEntity.setIsEnabled(true);
        userRepository.save(userEntity);
    }

    public boolean isValidRole(String role){
        return roleRepository.existsById(role);
    }
}
