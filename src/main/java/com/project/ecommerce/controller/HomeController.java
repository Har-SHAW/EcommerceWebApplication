package com.project.ecommerce.controller;

import com.project.ecommerce.binder.InitBinderClass;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController extends InitBinderClass {

    @RequestMapping("/")
    public String getHomePage(){
        return "home-page";
    }

    @RequestMapping("/loginPage")
    public String getLoginPage(Model model){
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
