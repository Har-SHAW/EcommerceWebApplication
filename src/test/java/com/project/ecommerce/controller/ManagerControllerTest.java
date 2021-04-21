package com.project.ecommerce.controller;


import com.project.ecommerce.dto.item.Item;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "shaw", authorities = {"ROLE_MANAGER"})
class ManagerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testValidationAddItem() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/managerDashboard/processAdd")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("itemName", "")
                .param("itemPrice", "")
                .sessionAttr("item", new Item())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("manager-items"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/manager-items.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("item", "itemName"))
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("item", "itemPrice"))
                .andExpect(MockMvcResultMatchers.model().attribute("item", Matchers.hasProperty("itemName", Matchers.nullValue())))
                .andExpect(MockMvcResultMatchers.model().attribute("item", Matchers.hasProperty("itemPrice", Matchers.nullValue())));
    }
}
