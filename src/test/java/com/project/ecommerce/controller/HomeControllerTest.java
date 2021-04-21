package com.project.ecommerce.controller;

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
@WithMockUser(username = "shaw", authorities = {"ROLE_USER", "ROLE_ADMIN", "ROLE_MANAGER", "ROLE_EMPLOYEE"})
class HomeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetHomePage() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("home-page"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/home-page.jsp"));
    }

    @Test
    void testGetAdminDashboard() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/adminDashboard"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admin-dashboard"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/admin-dashboard.jsp"));
    }

    @Test
    void testGetManagerDashboard() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/managerDashboard"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("manager-dashboard"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/manager-dashboard.jsp"));
    }

    @Test
    void testGetEmployeeDashboard() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/employeeDashboard"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("employee-dashboard"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/employee-dashboard.jsp"));
    }

    @Test
    void testGetUserDashboard() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/dashboard"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("dash-board"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/dash-board.jsp"));
    }
}
