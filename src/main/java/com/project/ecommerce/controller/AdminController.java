package com.project.ecommerce.controller;

import com.project.ecommerce.dto.user.AdminUser;
import com.project.ecommerce.dto.user.UserRole;
import com.project.ecommerce.entity.user.RolesEntity;
import com.project.ecommerce.entity.user.UserEntity;
import com.project.ecommerce.repository.RoleRepository;
import com.project.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/adminDashboard")
public class AdminController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmer);
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public List<AdminUser> getAllUsers(){
        List<UserEntity> userEntities = userRepository.findAll();

        List<AdminUser> adminUsers = new ArrayList<>();
        for (UserEntity userEntity : userEntities){
            adminUsers.add(new AdminUser(userEntity));
        }

        return adminUsers;
    }

    @RequestMapping("/showUsers")
    public String showUsers(Model model){

        model.addAttribute("users", getAllUsers());

        model.addAttribute("userRole", new UserRole());

        return "admin-users";
    }

    @RequestMapping("/changeRole")
    public String addRoleToUser(@Valid @ModelAttribute("userRole") UserRole userRole, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("users", getAllUsers());
            return "admin-users";
        }
        RolesEntity rolesEntity = roleRepository.findById(userRole.getRole()).orElse(null);
        UserEntity userEntity = userRepository.findById(userRole.getUsername()).orElse(null);
        assert userEntity != null;
        if (userRole.getAction().equals("Add")){
            userEntity.addRole(rolesEntity);
        }else{
            userEntity.getRolesEntities().remove(rolesEntity);
        }
        userRepository.save(userEntity);
        return "success-role";
    }
}
