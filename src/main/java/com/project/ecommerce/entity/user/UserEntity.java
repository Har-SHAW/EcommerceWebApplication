package com.project.ecommerce.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    protected String username;

    protected String password;

    @OneToOne
    @JoinColumn(name = "user_details_id")
    protected UserDetailsEntity userDetailsEntity;

    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "role")
    )
    protected List<RolesEntity> rolesEntities;

    public void addRole(RolesEntity rolesEntity){
        if (rolesEntities == null){
            this.rolesEntities = new ArrayList<>();
        }
        this.rolesEntities.add(rolesEntity);
    }
}
