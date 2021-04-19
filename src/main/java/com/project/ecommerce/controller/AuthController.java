package com.project.ecommerce.controller;

import com.project.ecommerce.binder.InitBinderClass;
import com.project.ecommerce.dto.user.UserLogin;
import com.project.ecommerce.dto.user.UserSignup;
import com.project.ecommerce.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class AuthController extends InitBinderClass {

    @Autowired
    AuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping("/signup")
    public String registerUser(Model model){
        model.addAttribute("user", new UserSignup());
        return "sign-up";
    }

    @PostMapping("/processSignup")
    public String processSignUp(@Valid @ModelAttribute("user") UserSignup theUser, BindingResult bindingResult, Model model){
        if (theUser.getConfirmPassword()!=null && !theUser.getPassword().equals(theUser.getConfirmPassword())){
            bindingResult.rejectValue("confirmPassword", "error.confirmPassword", "Password not matched");
        }
        if (bindingResult.hasErrors()){
            return "sign-up";
        }else {
            authService.registerTheUser(theUser);
            return "log-in";
        }
    }
}
