package com.project.ecommerce.dto.user;

import com.project.ecommerce.validation.OnlyGMail;
import com.project.ecommerce.validation.UniqueUsername;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class UserSignup {
    @UniqueUsername
    @NotNull(message = "is required")
    @Size(min = 4, message = "Minimum 4 characters")
    private String username;

    @NotNull(message = "is required")
    @Size(min = 6, message = "Minimum 6 characters")
    private String password;

    @NotNull(message = "is required")
    private String confirmPassword;

    @NotNull(message = "is required")
    @Min(value = 18, message = "You should be at least 18 for this")
    @Max(value = 60, message = "You should be under 60 for this")
    private Integer age;

    @NotNull(message = "is required")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Provide a valid Email")
    @OnlyGMail
    private String email;

    @NotNull(message = "is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Provide a valid phone Number")
    private String phoneNo;
}
