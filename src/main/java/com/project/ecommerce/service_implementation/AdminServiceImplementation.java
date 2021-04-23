package com.project.ecommerce.service_implementation;

import com.project.ecommerce.dto.user.AdminUser;
import com.project.ecommerce.dto.user.UserRole;
import com.project.ecommerce.entity.user.UserEntity;
import com.project.ecommerce.repository.RoleRepository;
import com.project.ecommerce.repository.UserRepository;
import com.project.ecommerce.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImplementation implements AdminService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<AdminUser> getAllUsers(){
        List<UserEntity> userEntities = userRepository.findAll();

        List<AdminUser> adminUsers = new ArrayList<>();
        for (UserEntity userEntity : userEntities){
            adminUsers.add(new AdminUser(userEntity));
        }

        return adminUsers;
    }

    @Override
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

    @Override
    public void disableUser(String username){
        var userEntity = userRepository.getOne(username);
        userEntity.setIsEnabled(false);
        userRepository.save(userEntity);
    }

    @Override
    public void enableUser(String username){
        var userEntity = userRepository.getOne(username);
        userEntity.setIsEnabled(true);
        userRepository.save(userEntity);
    }

    @Override
    public boolean isValidRole(String role){
        return roleRepository.existsById(role);
    }
}
