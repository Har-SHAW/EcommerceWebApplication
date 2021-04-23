package com.project.ecommerce.service;

import com.project.ecommerce.dto.order.Order;
import java.util.List;

public interface EmployeeService {
    List<Order> getAllOrders();
}
