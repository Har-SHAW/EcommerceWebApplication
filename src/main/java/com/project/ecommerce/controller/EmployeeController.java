package com.project.ecommerce.controller;

import com.project.ecommerce.dto.order.Order;
import com.project.ecommerce.entity.order.OrderEntity;
import com.project.ecommerce.repository.OrderRepository;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("employeeDashboard")
public class EmployeeController {
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmer);
    }

    @Autowired
    OrderRepository orderRepository;

    public List<Order> getAllOrders(){
        List<OrderEntity> orderEntities = orderRepository.findAll();
        List<Order> orders = new ArrayList<>();

        for (OrderEntity orderEntity : orderEntities){
            orders.add(new Order(orderEntity, orderEntity.getUserEntity()));
        }

        return orders;
    }

    @RequestMapping("/showOrders")
    public String showOrders(Model model){
        model.addAttribute("orders", getAllOrders());
        return "employee-orders";
    }
}
