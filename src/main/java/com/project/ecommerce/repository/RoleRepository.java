package com.project.ecommerce.repository;

import com.project.ecommerce.entity.user.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RolesEntity, String> {
}
