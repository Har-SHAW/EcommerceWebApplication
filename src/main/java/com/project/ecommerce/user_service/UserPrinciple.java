package com.project.ecommerce.user_service;

import com.project.ecommerce.entity.user.RolesEntity;
import com.project.ecommerce.entity.user.UserEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
public class UserPrinciple extends UserEntity implements UserDetails {

    private final List<GrantedAuthority> authorities;

    public UserPrinciple(String username, String password, List<GrantedAuthority> authorities, Boolean isEnabled) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.isEnabled = isEnabled;
    }

    public static UserPrinciple build(UserEntity user) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        Set<RolesEntity> rolesEntities = user.getRolesEntities();
        for (var rolesEntity : rolesEntities){
            authorities.add(new SimpleGrantedAuthority(rolesEntity.getRole()));
        }

        return new UserPrinciple(
                user.getUsername(),
                user.getPassword(),
                authorities,
                user.getIsEnabled()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
