package com.project.ecommerce.controller;

import com.project.ecommerce.binder.InitBinderClass;
import com.project.ecommerce.dto.user.UserRole;
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
        model.addAttribute("users", adminService.getAllUsers());
        return "admin-users";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("username") String username, Model model){
        adminService.deleteUser(username);
        model.addAttribute("users", adminService.getAllUsers());
        model.addAttribute("userRole", new UserRole());
        return "admin-users";
    }
}
