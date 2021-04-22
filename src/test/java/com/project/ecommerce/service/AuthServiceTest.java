package com.project.ecommerce.service;

import com.project.ecommerce.dto.user.UserSignup;
import com.project.ecommerce.entity.user.UserEntity;
import com.project.ecommerce.repository.RoleRepository;
import com.project.ecommerce.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class AuthServiceTest {
    @MockBean
    UserRepository userRepository;

    @MockBean
    RoleRepository roleRepository;

    @Autowired
    AuthService authService;

    @Test
    void testRegisterTheUser(){
        UserSignup userSignup = new UserSignup();
        userSignup.setUsername("shaw");
        userSignup.setAge(21);
        userSignup.setEmail("harsha@gmail.com");
        userSignup.setPassword("shaw");
        userSignup.setPhoneNo("9090909090");

        authService.registerTheUser(userSignup);

        Mockito.verify(roleRepository, Mockito.times(1)).getOne("ROLE_USER");
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(UserEntity.class));
    }

    @Test
    void testUserExist(){
        Mockito.when(userRepository.existsById("shaw")).thenReturn(true);
        Assertions.assertThat(authService.userExist("shaw")).isTrue();
    }
}
