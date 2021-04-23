package com.project.ecommerce.service;

import com.project.ecommerce.dto.user.UserSignup;

public interface AuthService {
    void registerTheUser(UserSignup theUser);

    boolean userExist(String username);
}
