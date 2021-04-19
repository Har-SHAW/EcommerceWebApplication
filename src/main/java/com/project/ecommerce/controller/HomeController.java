package com.project.ecommerce.controller;

import com.project.ecommerce.binder.InitBinderClass;
import com.project.ecommerce.jsp_pages.JspPages;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController extends InitBinderClass {

    @RequestMapping("/")
    public String getHomePage(){
        return JspPages.HOME_PAGE;
    }

    @RequestMapping("/loginPage")
    public String getLoginPage(Model model){
    return JspPages.LOG_IN;
    }

    @RequestMapping("/adminDashboard")
    public String getAdminDashboard(){
        return JspPages.ADMIN_DASHBOARD;
    }

    @RequestMapping("/managerDashboard")
    public String getManagerDashboard(){
        return JspPages.MANAGER_DASHBOARD;
    }

    @RequestMapping("/employeeDashboard")
    public String getEmployeeDashboard(){ return JspPages.EMPLOYEE_DASHBOARD; }

    @RequestMapping("/access-denied")
    public String getAccessDenied(){
        return JspPages.ACCESS_DENIED;
    }
}
