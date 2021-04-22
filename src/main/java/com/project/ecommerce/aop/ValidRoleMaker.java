package com.project.ecommerce.aop;

import com.project.ecommerce.binder.InitBinderClass;
import com.project.ecommerce.dto.user.UserRole;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidRoleMaker extends InitBinderClass {
    @Before("execution(* com.project.ecommerce.controller.AdminController.addRoleToUser(..))")
    public void validRole(JoinPoint joinPoint){
        Object[] objects = joinPoint.getArgs();

        for (Object object: objects){
            if (object instanceof UserRole){
                var userRole = (UserRole) object;
                String role = userRole.getRole();
                if (role!=null) {
                    if (!role.contains("_")){
                        role = "ROLE_" + role;
                    }
                    role = role.toUpperCase();
                }
                userRole.setRole(role);
            }
        }
    }
}
