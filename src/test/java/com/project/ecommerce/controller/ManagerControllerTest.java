package com.project.ecommerce.controller;


import com.project.ecommerce.dto.item.Item;
import com.project.ecommerce.entity.item.ItemEntity;
import com.project.ecommerce.service.ManagerService;
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

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "shaw", authorities = {"ROLE_MANAGER"})
class ManagerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ManagerService managerService;

    @Test
    void testValidationAddItem() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/managerDashboard/processAdd")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("itemName", "")
                .param("itemPrice", "")
                .sessionAttr("item", new Item())
                .with(SecurityMockMvcRequestPostProcessors.csrf())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("manager-items"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/manager-items.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("item", "itemName"))
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("item", "itemPrice"))
                .andExpect(MockMvcResultMatchers.model().attribute("item", Matchers.hasProperty("itemName", Matchers.nullValue())))
                .andExpect(MockMvcResultMatchers.model().attribute("item", Matchers.hasProperty("itemPrice", Matchers.nullValue())));
    }

    @Test
    void testValidationEditItem() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/managerDashboard/processEdit")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("itemName", "")
                .param("itemPrice", "")
                .sessionAttr("item", new Item())
                .with(SecurityMockMvcRequestPostProcessors.csrf())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("edit-item"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/edit-item.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("item", "itemName"))
                .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("item", "itemPrice"))
                .andExpect(MockMvcResultMatchers.model().attribute("item", Matchers.hasProperty("itemName", Matchers.nullValue())))
                .andExpect(MockMvcResultMatchers.model().attribute("item", Matchers.hasProperty("itemPrice", Matchers.nullValue())));
    }

    @Test
    void testAddItem() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/managerDashboard/processAdd")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("itemName", "dove")
                .param("itemPrice", "1000")
                .sessionAttr("item", new Item())
                .with(SecurityMockMvcRequestPostProcessors.csrf())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("manager-items"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/manager-items.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeHasNoErrors());

        Mockito.verify(managerService, Mockito.times(1)).findByName(Mockito.anyString());
        Mockito.verify(managerService, Mockito.times(1)).addItem(Mockito.any(Item.class));
        Mockito.verify(managerService, Mockito.times(1)).getAllItems();
    }

    @Test
    void testEditItem() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/managerDashboard/processEdit")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("itemName", "dove")
                .param("itemPrice", "1000")
                .sessionAttr("item", new Item())
                .with(SecurityMockMvcRequestPostProcessors.csrf())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("manager-items"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/manager-items.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeHasNoErrors());

        Mockito.verify(managerService, Mockito.times(1)).editItem(Mockito.any(Item.class));
        Mockito.verify(managerService, Mockito.times(1)).getAllItems();
    }

    @Test
    void testShowItems() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/managerDashboard/showItems")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("itemName", "dove")
                .param("itemPrice", "1000")
                .sessionAttr("item", new Item())
                .with(SecurityMockMvcRequestPostProcessors.csrf())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("manager-items"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/manager-items.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeHasNoErrors());

        Mockito.verify(managerService, Mockito.times(1)).getAllItems();
    }

    @Test
    void testNoStockItem() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/managerDashboard/noStock")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("itemId", "1")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("manager-items"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/manager-items.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeHasNoErrors());

        Mockito.verify(managerService, Mockito.times(1)).setNoStock(1L);
        Mockito.verify(managerService, Mockito.times(1)).getAllItems();
    }

    @Test
    void testInStockItem() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/managerDashboard/inStock")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("itemId", "1")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("manager-items"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/manager-items.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeHasNoErrors());

        Mockito.verify(managerService, Mockito.times(1)).setInStock(1L);
        Mockito.verify(managerService, Mockito.times(1)).getAllItems();
    }
}
