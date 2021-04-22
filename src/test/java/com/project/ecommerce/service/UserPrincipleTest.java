package com.project.ecommerce.service;

import com.project.ecommerce.entity.user.RolesEntity;
import com.project.ecommerce.entity.user.UserEntity;
import com.project.ecommerce.user_service.UserPrinciple;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserPrincipleTest {
    @Test
    void testUserPrinciple(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("shaw");
        userEntity.setPassword("shady");
        RolesEntity rolesEntity = new RolesEntity();
        rolesEntity.setRole("ROLE_ADMIN");
        userEntity.addRole(rolesEntity);
        userEntity.setIsEnabled(true);
        UserPrinciple userPrinciple = UserPrinciple.build(userEntity);
        Assertions.assertThat(userPrinciple.isAccountNonExpired()).isTrue();
        Assertions.assertThat(userPrinciple.isEnabled()).isTrue();
        Assertions.assertThat(userPrinciple.isAccountNonLocked()).isTrue();
        Assertions.assertThat(userPrinciple.isCredentialsNonExpired()).isTrue();
        Assertions.assertThat(userPrinciple.getUsername()).isEqualTo("shaw");
        Assertions.assertThat(userPrinciple.getPassword()).isEqualTo("shady");
        Assertions.assertThat(userPrinciple.getAuthorities()).isNotEmpty();
    }
}
