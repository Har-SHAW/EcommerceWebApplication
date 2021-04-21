package com.project.ecommerce.dto.user;

import com.project.ecommerce.entity.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SimpleUser {
    private String username;
    private String email;
    private String phoneNo;

    public SimpleUser(UserEntity userEntity){
        this.username = userEntity.getUsername();
        this.email = userEntity.getUserDetailsEntity().getEmail();
        this.phoneNo = userEntity.getUserDetailsEntity().getPhoneNo();
    }
}
