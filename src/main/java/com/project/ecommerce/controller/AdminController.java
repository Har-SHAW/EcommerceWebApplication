package com.project.ecommerce.controller;

import com.project.ecommerce.binder.InitBinderClass;
import com.project.ecommerce.dto.user.UserRole;
import com.project.ecommerce.jsp_pages.JspPages;
import com.project.ecommerce.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/adminDashboard")
public class AdminController extends InitBinderClass {

    @Autowired
    AdminService adminService;

    private static final String USERS = "users";
    private static final String USER_ROLE = "userRole";

    @RequestMapping("/showUsers")
    public String showUsers(Model model){

        model.addAttribute(USERS, adminService.getAllUsers());

        model.addAttribute(USER_ROLE, new UserRole());

        return JspPages.ADMIN_USERS;
    }

    @RequestMapping("/changeRole")
    public String addRoleToUser(@Valid @ModelAttribute("userRole") UserRole userRole, BindingResult bindingResult, Model model){

        if (!bindingResult.hasErrors() && !adminService.isValidRole(userRole.getRole())){
            bindingResult.rejectValue("role", "error.role", "No such role");
        }

        if (!bindingResult.hasErrors()){
            adminService.changeRole(userRole);
            model.addAttribute(USER_ROLE, new UserRole());
        }

        model.addAttribute(USERS, adminService.getAllUsers());

        return JspPages.ADMIN_USERS;
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("username") String username, Model model){
        adminService.deleteUser(username);
        model.addAttribute(USERS, adminService.getAllUsers());
        model.addAttribute(USER_ROLE, new UserRole());
        return JspPages.ADMIN_USERS;
    }
}
