package com.project.ecommerce.service;

import com.project.ecommerce.dto.user.AdminUser;
import com.project.ecommerce.dto.user.UserRole;
import java.util.List;

public interface AdminService {
    List<AdminUser> getAllUsers();

    void changeRole(UserRole userRole);

    void disableUser(String username);

    void enableUser(String username);

    boolean isValidRole(String role);
}
