package com.project.ecommerce.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "shaw", authorities = {"ROLE_USER"})
    void testUserWithEmployee() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/employeeDashboard")).andExpect(MockMvcResultMatchers.status().is(403));
    }

    @Test
    @WithMockUser(username = "shaw", authorities = {"ROLE_MANAGER"})
    void testManagerWithEmployee() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/employeeDashboard")).andExpect(MockMvcResultMatchers.status().is(403));
    }

    @Test
    @WithMockUser(username = "shaw", authorities = {"ROLE_EMPLOYEE"})
    void testEmployeeWithEmployee() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/employeeDashboard")).andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    @WithMockUser(username = "shaw", authorities = {"ROLE_ADMIN"})
    void testAdminWithEmployee() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/employeeDashboard")).andExpect(MockMvcResultMatchers.status().is(403));
    }
}
