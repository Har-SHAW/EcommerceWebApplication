package com.project.ecommerce.entity.user;

import com.project.ecommerce.entity.order.OrderEntity;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_details_id")
    protected UserDetailsEntity userDetailsEntity;

    @OneToMany(mappedBy = "userEntity")
    private List<OrderEntity> orderEntities;

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
