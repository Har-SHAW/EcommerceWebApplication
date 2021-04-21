package com.project.ecommerce.controller;

import com.project.ecommerce.binder.InitBinderClass;
import com.project.ecommerce.jsp_pages.JspPages;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController extends InitBinderClass {

    @GetMapping("/")
    public String getHomePage(){
        return JspPages.HOME_PAGE;
    }

    @GetMapping("/loginPage")
    public String getLoginPage(Model model){
    return JspPages.LOG_IN;
    }

    @GetMapping("/adminDashboard")
    public String getAdminDashboard(){
        return JspPages.ADMIN_DASHBOARD;
    }

    @GetMapping("/managerDashboard")
    public String getManagerDashboard(){
        return JspPages.MANAGER_DASHBOARD;
    }

    @GetMapping("/employeeDashboard")
    public String getEmployeeDashboard(){ return JspPages.EMPLOYEE_DASHBOARD; }

    @GetMapping("/access-denied")
    public String getAccessDenied(){
        return JspPages.ACCESS_DENIED;
    }
}
