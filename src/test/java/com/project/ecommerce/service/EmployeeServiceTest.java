package com.project.ecommerce.service;

import com.project.ecommerce.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

@SpringBootTest
class EmployeeServiceTest {
    @Autowired
    EmployeeService employeeService;

    @MockBean
    OrderRepository orderRepository;

    @Test
    void getAllOrders(){
        Mockito.when(orderRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertThat(employeeService.getAllOrders()).isEmpty();
        Mockito.verify(orderRepository, Mockito.times(1)).findAll();
    }
}
