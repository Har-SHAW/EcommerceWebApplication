package com.project.ecommerce.service_implementation;

import com.project.ecommerce.dto.order.Order;
import com.project.ecommerce.entity.order.OrderEntity;
import com.project.ecommerce.repository.OrderRepository;
import com.project.ecommerce.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders(){
        List<OrderEntity> orderEntities = orderRepository.findAll();
        List<Order> orders = new ArrayList<>();

        for (OrderEntity orderEntity : orderEntities){
            orders.add(new Order(orderEntity, orderEntity.getUserEntity()));
        }

        return orders;
    }
}
