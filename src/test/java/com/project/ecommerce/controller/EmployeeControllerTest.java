package com.project.ecommerce.controller;

import com.project.ecommerce.service_implementation.EmployeeServiceImplementation;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "shaw", authorities = {"ROLE_EMPLOYEE"})
class EmployeeControllerTest {

    @MockBean
    EmployeeServiceImplementation employeeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testShowOrder() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/employeeDashboard/showOrders")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("employee-orders"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/employee-orders.jsp"));

        Mockito.verify(employeeService, Mockito.times(1)).getAllOrders();
    }
}
