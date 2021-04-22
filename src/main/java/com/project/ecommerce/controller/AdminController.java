package com.project.ecommerce.controller;

import com.project.ecommerce.binder.InitBinderClass;
import com.project.ecommerce.dto.user.UserRole;
import com.project.ecommerce.jsp_pages.JspPages;
import com.project.ecommerce.service.AdminService;
import com.project.ecommerce.service.AuthService;
import com.project.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/adminDashboard")
public class AdminController extends InitBinderClass {

    @Autowired
    AdminService adminService;

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    private static final String USERS = "users";
    private static final String USER_ROLE = "userRole";

    @GetMapping("/showUsers")
    public String showUsers(Model model){

        model.addAttribute(USERS, adminService.getAllUsers());

        model.addAttribute(USER_ROLE, new UserRole());

        return JspPages.ADMIN_USERS;
    }

    @PostMapping("/changeRole")
    public String addRoleToUser(@Valid @ModelAttribute("userRole") UserRole userRole, BindingResult bindingResult, Model model){

        if (!bindingResult.hasErrors() && !authService.userExist(userRole.getUsername())){
            bindingResult.rejectValue("username", "error.username", "No such user");
        }

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

    @GetMapping("/disableUser")
    public String disableUser(@RequestParam("username") String username, Model model){
        if (!userService.getUsernameFromAuth().equals(username)) {
            adminService.disableUser(username);
        }
        model.addAttribute(USERS, adminService.getAllUsers());
        model.addAttribute(USER_ROLE, new UserRole());
        return JspPages.ADMIN_USERS;
    }

    @GetMapping("/enableUser")
    public String enableUser(@RequestParam("username") String username, Model model){
        adminService.enableUser(username);
        model.addAttribute(USERS, adminService.getAllUsers());
        model.addAttribute(USER_ROLE, new UserRole());
        return JspPages.ADMIN_USERS;
    }
}
