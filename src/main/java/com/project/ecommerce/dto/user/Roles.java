package com.project.ecommerce.dto.user;

import com.project.ecommerce.entity.user.RolesEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Roles {
    private String role;

    public Roles(RolesEntity rolesEntity){
        this.role = rolesEntity.getRole();
    }
}
