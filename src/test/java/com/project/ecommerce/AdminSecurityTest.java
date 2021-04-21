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
class AdminSecurityTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "shaw", authorities = {"ROLE_USER"})
    void testUserWithAdmin() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/adminDashboard")).andExpect(MockMvcResultMatchers.status().is(403));
    }

    @Test
    @WithMockUser(username = "shaw", authorities = {"ROLE_MANAGER"})
    void testManagerWithAdmin() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/adminDashboard")).andExpect(MockMvcResultMatchers.status().is(403));
    }

    @Test
    @WithMockUser(username = "shaw", authorities = {"ROLE_EMPLOYEE"})
    void testEmployeeWithAdmin() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/adminDashboard")).andExpect(MockMvcResultMatchers.status().is(403));
    }

    @Test
    @WithMockUser(username = "shaw", authorities = {"ROLE_ADMIN"})
    void testAdminWithAdmin() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/adminDashboard")).andExpect(MockMvcResultMatchers.status().is(200));
    }
}
