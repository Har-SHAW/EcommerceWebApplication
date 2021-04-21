package com.project.ecommerce;


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
class ManagerSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "shaw", authorities = {"ROLE_USER"})
    void testUserWithManager() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/managerDashboard")).andExpect(MockMvcResultMatchers.status().is(403));
    }

    @Test
    @WithMockUser(username = "shaw", authorities = {"ROLE_MANAGER"})
    void testManagerWithManager() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/managerDashboard")).andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    @WithMockUser(username = "shaw", authorities = {"ROLE_EMPLOYEE"})
    void testEmployeeWithManager() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/managerDashboard")).andExpect(MockMvcResultMatchers.status().is(403));
    }

    @Test
    @WithMockUser(username = "shaw", authorities = {"ROLE_ADMIN"})
    void testAdminWithManager() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/managerDashboard")).andExpect(MockMvcResultMatchers.status().is(403));
    }
}
