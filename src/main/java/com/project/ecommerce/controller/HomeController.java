package com.project.ecommerce.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmer);
    }

    @RequestMapping("/")
    public String getHomePage(){
        return "home-page";
    }

    @RequestMapping("/loginPage")
    public String getLoginPage(){
        return "log-in";
    }

    @RequestMapping("/adminDashboard")
    public String getAdminDashboard(){
        return "admin-dashboard";
    }

    @RequestMapping("/managerDashboard")
    public String getManagerDashboard(){
        return "manager-dashboard";
    }

    @RequestMapping("/employeeDashboard")
    public String getEmployeeDashboard(){ return "employee-dashboard"; }

    @RequestMapping("/access-denied")
    public String getAccessDenied(){
        return "access-denied";
    }
}
