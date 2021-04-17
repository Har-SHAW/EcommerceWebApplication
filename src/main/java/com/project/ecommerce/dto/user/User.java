package com.project.ecommerce.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class User {
    @NotNull(message = "username is required")
    @Size(min = 4, message = "at least 4 characters")
    private String username;

    @NotNull(message = "password is required")
    @Size(min = 6, message = "at least 6 characters")
    private String password;
}
