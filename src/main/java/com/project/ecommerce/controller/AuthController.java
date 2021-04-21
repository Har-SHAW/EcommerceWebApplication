package com.project.ecommerce.controller;

import com.project.ecommerce.binder.InitBinderClass;
import com.project.ecommerce.dto.user.UserSignup;
import com.project.ecommerce.jsp_pages.JspPages;
import com.project.ecommerce.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class AuthController extends InitBinderClass {

    @Autowired
    AuthService authService;

    @GetMapping("/signup")
    public String registerUser(Model model){
        model.addAttribute("user", new UserSignup());
        return JspPages.SIGN_UP;
    }

    @PostMapping(value = "/processSignup")
    public String processSignUp(@Valid @ModelAttribute("user") UserSignup theUser, BindingResult bindingResult, Model model){
        if (theUser.getUsername()!=null && authService.userExist(theUser.getUsername())){
            bindingResult.rejectValue("username", "error.username", "Username already taken");
        }
        if (theUser.getConfirmPassword()!=null && !theUser.getPassword().equals(theUser.getConfirmPassword())){
            bindingResult.rejectValue("confirmPassword", "error.confirmPassword", "Password not matched");
        }
        if (bindingResult.hasErrors()){
            return JspPages.SIGN_UP;
        }else {
            authService.registerTheUser(theUser);
            return JspPages.LOG_IN;
        }
    }
}
