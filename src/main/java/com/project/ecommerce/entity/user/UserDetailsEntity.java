package com.project.ecommerce.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_details")
@Getter
@Setter
@NoArgsConstructor
public class UserDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userDetailsId;

    private Integer age;

    private String email;

    private String phoneNo;
}
