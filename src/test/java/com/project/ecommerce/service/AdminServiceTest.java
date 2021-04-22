package com.project.ecommerce.service;

import com.project.ecommerce.dto.user.UserRole;
import com.project.ecommerce.entity.user.RolesEntity;
import com.project.ecommerce.entity.user.UserEntity;
import com.project.ecommerce.repository.RoleRepository;
import com.project.ecommerce.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

@SpringBootTest
class AdminServiceTest {

    @MockBean
    UserRepository userRepository;

    @MockBean
    RoleRepository roleRepository;

    @Autowired
    AdminService adminService;

    @Test
    void testDisable(){
        Mockito.when(userRepository.getOne("shaw")).thenReturn(new UserEntity());
        adminService.disableUser("shaw");
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(UserEntity.class));
        Mockito.verify(userRepository, Mockito.times(1)).getOne("shaw");
    }

    @Test
    void testEnable(){
        Mockito.when(userRepository.getOne("shaw")).thenReturn(new UserEntity());
        adminService.enableUser("shaw");
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(UserEntity.class));
        Mockito.verify(userRepository, Mockito.times(1)).getOne("shaw");
    }

    @Test
    void testIsValidRole(){
        Mockito.when(roleRepository.existsById("ROLE_ADMIN")).thenReturn(true);

        Assertions.assertThat(adminService.isValidRole("ROLE_ADMIN")).isTrue();

        Mockito.verify(roleRepository, Mockito.times(1)).existsById("ROLE_ADMIN");
    }

    @Test
    void testGetAllUsers(){
        Mockito.when(userRepository.findAll()).thenReturn(new ArrayList<>());

        Assertions.assertThat(adminService.getAllUsers()).isEmpty();

        Mockito.verify(userRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testChangeRole(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("shaw");
        RolesEntity rolesEntity = new RolesEntity();
        rolesEntity.setRole("ROLE_ADMIN");

        userEntity.addRole(rolesEntity);

        RolesEntity rolesEntity2 = new RolesEntity();
        rolesEntity2.setRole("ROLE_USER");

        Mockito.when(userRepository.getOne("shaw")).thenReturn(userEntity);
        Mockito.when(roleRepository.getOne("ROLE_ADMIN")).thenReturn(rolesEntity2);

        UserRole userRole = new UserRole();
        userRole.setRole("ROLE_ADMIN");
        userRole.setUsername("shaw");
        userRole.setAction("Add");

        adminService.changeRole(userRole);

        Assertions.assertThat(userEntity.getRolesEntities()).hasSize(2);

        userRole.setAction("Remove");

        adminService.changeRole(userRole);

        Assertions.assertThat(userEntity.getRolesEntities()).hasSize(1);
;    }
}
