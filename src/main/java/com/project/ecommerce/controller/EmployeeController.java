package com.project.ecommerce.controller;

import com.project.ecommerce.binder.InitBinderClass;
import com.project.ecommerce.jsp_pages.JspPages;
import com.project.ecommerce.service_implementation.EmployeeServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("employeeDashboard")
public class EmployeeController extends InitBinderClass {

    @Autowired
    EmployeeServiceImplementation employeeService;

    @GetMapping("/showOrders")
    public String showOrders(Model model){
        model.addAttribute("orders", employeeService.getAllOrders());
        return JspPages.EMPLOYEE_ORDERS;
    }
}
