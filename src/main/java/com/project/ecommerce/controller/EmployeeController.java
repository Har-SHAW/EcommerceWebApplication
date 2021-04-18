package com.project.ecommerce.controller;

import com.project.ecommerce.binder.InitBinderClass;
import com.project.ecommerce.dto.order.Order;
import com.project.ecommerce.entity.order.OrderEntity;
import com.project.ecommerce.repository.OrderRepository;
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
