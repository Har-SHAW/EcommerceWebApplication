package com.project.ecommerce.controller;

import com.project.ecommerce.binder.InitBinderClass;
import com.project.ecommerce.dto.user.AdminUser;
import com.project.ecommerce.dto.user.UserRole;
import com.project.ecommerce.entity.user.RolesEntity;
import com.project.ecommerce.entity.user.UserEntity;
import com.project.ecommerce.repository.RoleRepository;
import com.project.ecommerce.repository.UserRepository;
import com.project.ecommerce.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/adminDashboard")
public class AdminController extends InitBinderClass {

    @Autowired
    AdminService adminService;

    @RequestMapping("/showUsers")
    public String showUsers(Model model){

        model.addAttribute("users", adminService.getAllUsers());

        model.addAttribute("userRole", new UserRole());

        return "admin-users";
    }

    @RequestMapping("/changeRole")
    public String addRoleToUser(@Valid @ModelAttribute("userRole") UserRole userRole, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("users", adminService.getAllUsers());
            return "admin-users";
        }
        adminService.changeRole(userRole);
        return "success-role";
    }
}
