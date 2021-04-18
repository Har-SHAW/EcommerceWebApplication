package com.project.ecommerce.controller;

import com.project.ecommerce.binder.InitBinderClass;
import com.project.ecommerce.dto.order.Order;
import com.project.ecommerce.entity.order.OrderEntity;
import com.project.ecommerce.repository.OrderRepository;
import com.project.ecommerce.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("employeeDashboard")
public class EmployeeController extends InitBinderClass {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/showOrders")
    public String showOrders(Model model){
        model.addAttribute("orders", employeeService.getAllOrders());
        return "employee-orders";
    }
}
