package com.project.ecommerce.dto.user;

import com.project.ecommerce.entity.user.RolesEntity;
import com.project.ecommerce.entity.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class AdminUser {
    private String username;
    private Set<Roles> roles;
    private String email;
    private String phoneNo;
    private Integer age;

    public AdminUser(UserEntity userEntity){
        this.username = userEntity.getUsername();
        if (this.roles == null){
            this.roles = new HashSet<>();
        }
        for (RolesEntity rolesEntity : userEntity.getRolesEntities()){
            this.roles.add(new Roles(rolesEntity));
        }
        this.email = userEntity.getUserDetailsEntity().getEmail();
        this.phoneNo = userEntity.getUserDetailsEntity().getPhoneNo();
        this.age = userEntity.getUserDetailsEntity().getAge();
    }
}
