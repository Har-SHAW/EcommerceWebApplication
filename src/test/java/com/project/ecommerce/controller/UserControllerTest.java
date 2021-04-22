package com.project.ecommerce.controller;


import com.project.ecommerce.dto.item.Item;
import com.project.ecommerce.dto.order.OrderItem;
import com.project.ecommerce.model.CartModel;
import com.project.ecommerce.service.UserService;
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
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "shaw", authorities = {"ROLE_USER"})
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Test
    void testPLaceOrder() throws Exception {
        CartModel cartModel = new CartModel();
        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = new OrderItem();
        Item item = new Item();
        item.setItemId(1L);
        item.setItemName("dove");
        item.setItemPrice(1000D);
        orderItem.setItem(item);
        orderItem.setQuantity(2);
        cartModel.addItem(orderItem);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/placeOrder")
                .sessionAttr("cart", cartModel)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("success-order"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/success-order.jsp"));

        Mockito.verify(userService, Mockito.times(1)).placeOrder(Mockito.any(CartModel.class));
    }

    @Test
    void testShowOrder() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/showOrders")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user-orders"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/user-orders.jsp"));

        Mockito.verify(userService, Mockito.times(1)).getOrdersOfUser();
    }
}
