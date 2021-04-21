package com.project.ecommerce.dto.user;

import com.project.ecommerce.validation.ValidUsername;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UserRole {
    @NotNull(message = "is required")
    @ValidUsername
    private String username;

    @NotNull(message = "is required")
    private String role;

    @NotNull(message = "is required")
    private String action;
}
