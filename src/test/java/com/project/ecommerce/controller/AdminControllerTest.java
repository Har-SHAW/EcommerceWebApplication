package com.project.ecommerce.controller;

import com.project.ecommerce.dto.user.UserRole;
import com.project.ecommerce.service.AdminService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "shaw", authorities = {"ROLE_ADMIN"})
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AdminService adminService;

    @Test
    void testValidationChangeRole() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/adminDashboard/changeRole")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "")
                .param("role", "")
                .param("action", "")
                .sessionAttr("userRole", new UserRole())
                .with(SecurityMockMvcRequestPostProcessors.csrf())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admin-users"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/admin-users.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("userRole", "username"))
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("userRole", "role"))
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("userRole", "action"))
                .andExpect(MockMvcResultMatchers.model().attribute("userRole", Matchers.hasProperty("username", Matchers.nullValue())))
                .andExpect(MockMvcResultMatchers.model().attribute("userRole", Matchers.hasProperty("role", Matchers.nullValue())))
                .andExpect(MockMvcResultMatchers.model().attribute("userRole", Matchers.hasProperty("action", Matchers.nullValue())));
    }

    @Test
    void testChangeAopRole() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/adminDashboard/changeRole")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "shaw")
                .param("role", "user")
                .param("action", "Add")
                .sessionAttr("userRole", new UserRole())
                .with(SecurityMockMvcRequestPostProcessors.csrf())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admin-users"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/admin-users.jsp"))
                .andExpect(MockMvcResultMatchers.model().attribute("userRole", Matchers.hasProperty("role", Matchers.is("ROLE_USER"))));
    }

    @Test
    void testRoleValidationChangeRole() throws Exception {
        Mockito.when(adminService.isValidRole("ROLE_USER")).thenReturn(false);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/adminDashboard/changeRole")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "shaw")
                .param("role", "user")
                .param("action", "Add")
                .sessionAttr("userRole", new UserRole())
                .with(SecurityMockMvcRequestPostProcessors.csrf())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admin-users"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/admin-users.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("userRole", "role"));

        Mockito.verify(adminService, Mockito.times(1)).isValidRole("ROLE_USER");
        Mockito.verify(adminService, Mockito.times(1)).getAllUsers();
        Mockito.verifyNoMoreInteractions(adminService);
    }

    @Test
    void testSuccessCases() throws Exception {
        Mockito.when(adminService.isValidRole("ROLE_USER")).thenReturn(true);
         this.mockMvc.perform(MockMvcRequestBuilders.post("/adminDashboard/changeRole")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "shaw")
                .param("role", "user")
                .param("action", "Add")
                .sessionAttr("userRole", new UserRole())
                 .with(SecurityMockMvcRequestPostProcessors.csrf())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admin-users"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/admin-users.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeHasNoErrors("userRole"));

        Mockito.verify(adminService, Mockito.times(1)).isValidRole("ROLE_USER");
        Mockito.verify(adminService, Mockito.times(1)).getAllUsers();
    }

    @Test
    void testInitialFormEmpty() throws Exception {
        Mockito.when(adminService.getAllUsers()).thenReturn(new ArrayList<>());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/adminDashboard/showUsers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admin-users"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/admin-users.jsp"))
                .andExpect(MockMvcResultMatchers.model().attribute("userRole", Matchers.hasProperty("username", Matchers.nullValue())))
                .andExpect(MockMvcResultMatchers.model().attribute("userRole", Matchers.hasProperty("username", Matchers.nullValue())));
        Mockito.verify(adminService, Mockito.times(1)).getAllUsers();
        Mockito.verifyNoMoreInteractions(adminService);
    }
}
