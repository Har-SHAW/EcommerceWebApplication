package com.project.ecommerce.controller;

import com.project.ecommerce.dto.item.Item;
import com.project.ecommerce.dto.order.OrderItem;
import com.project.ecommerce.model.CartModel;
import com.project.ecommerce.service.CartService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
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
@WithMockUser(username = "shaw", authorities = {"ROLE_USER", "ROLE_ADMIN", "ROLE_MANAGER", "ROLE_EMPLOYEE"})
class CartControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CartService cartService;

    CartModel getCart(){
        Item item = new Item();
        item.setItemId(2L);
        item.setItemName("dove");
        item.setItemPrice(2000D);
        item.setIsOutOfStock(false);
        CartModel cartModel = new CartModel();
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setQuantity(2);
        cartModel.addItem(orderItem);

        return cartModel;
    }

    @BeforeEach
    void setUp(){
        Item returnItem = new Item();
        returnItem.setItemId(1L);
        returnItem.setItemName("love");
        returnItem.setItemPrice(3000D);
        returnItem.setIsOutOfStock(false);
        OrderItem returnOrderItem = new OrderItem();
        returnOrderItem.setItem(returnItem);
        returnOrderItem.setQuantity(1);

        Mockito.when(cartService.getOrderItem(1L)).thenReturn(returnOrderItem);
    }

    @Test
    void testAddCart() throws Exception {
        CartModel cartModel = getCart();
        this.mockMvc.perform(MockMvcRequestBuilders.get("/addItem")
                .sessionAttr("cart", cartModel)
                .param("itemId", "1")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("dash-board"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/dash-board.jsp"))
                .andExpect(MockMvcResultMatchers.model().attribute("cart", Matchers.hasProperty("totalPrice", Matchers.is(7000D))))
                .andExpect(MockMvcResultMatchers.model().attribute("cart", Matchers.hasProperty("orderItems", Matchers.hasItem(
                        Matchers.allOf(
                                Matchers.hasProperty("item", Matchers.allOf(
                                        Matchers.hasProperty("itemId", Matchers.is(2L)),
                                        Matchers.hasProperty("itemName", Matchers.is("dove")),
                                        Matchers.hasProperty("itemPrice", Matchers.is(2000D))
                                )),
                                Matchers.hasProperty("quantity", Matchers.is(2))
                        )
                ))))
                .andExpect(MockMvcResultMatchers.model().attribute("cart", Matchers.hasProperty("orderItems", Matchers.hasItem(
                        Matchers.allOf(
                                Matchers.hasProperty("item", Matchers.allOf(
                                        Matchers.hasProperty("itemId", Matchers.is(1L)),
                                        Matchers.hasProperty("itemName", Matchers.is("love")),
                                        Matchers.hasProperty("itemPrice", Matchers.is(3000D))
                                )),
                                Matchers.hasProperty("quantity", Matchers.is(1))
                        )
                ))));

    }

    @Test
    void testAddCartOutOfStock() throws Exception {
        Item returnItem = new Item();
        returnItem.setItemId(5L);
        returnItem.setItemName("love");
        returnItem.setItemPrice(3000D);
        returnItem.setIsOutOfStock(true);
        OrderItem returnOrderItem = new OrderItem();
        returnOrderItem.setItem(returnItem);
        returnOrderItem.setQuantity(1);

        Mockito.when(cartService.getOrderItem(5L)).thenReturn(returnOrderItem);

        CartModel cartModel = getCart();
        this.mockMvc.perform(MockMvcRequestBuilders.get("/addItem")
                .sessionAttr("cart", cartModel)
                .param("itemId", "5")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("dash-board"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/dash-board.jsp"))
                .andExpect(MockMvcResultMatchers.model().attribute("cart", Matchers.hasProperty("totalPrice", Matchers.is(4000D))))
                .andExpect(MockMvcResultMatchers.model().attribute("cart", Matchers.hasProperty("orderItems", Matchers.hasSize(1))));

    }

    @Test
    void testIncrementItem() throws Exception {

        CartModel cartModel = getCart();

        Mockito.when(cartService.findOrderItem(cartModel.getOrderItems(), 2L)).thenReturn(cartModel.getOrderItems().get(0));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/incrementItem")
                .sessionAttr("cart", cartModel)
                .param("itemId", "2")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("dash-board"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/dash-board.jsp"))
                .andExpect(MockMvcResultMatchers.model().attribute("cart", Matchers.hasProperty("totalPrice", Matchers.is(6000D))))
                .andExpect(MockMvcResultMatchers.model().attribute("cart", Matchers.hasProperty("orderItems", Matchers.hasItem(
                        Matchers.allOf(
                                Matchers.hasProperty("item", Matchers.allOf(
                                        Matchers.hasProperty("itemId", Matchers.is(2L)),
                                        Matchers.hasProperty("itemName", Matchers.is("dove")),
                                        Matchers.hasProperty("itemPrice", Matchers.is(2000D))
                                )),
                                Matchers.hasProperty("quantity", Matchers.is(3))
                        )
                ))));

    }

    @Test
    void testDecrementItem() throws Exception {

        CartModel cartModel = getCart();

        Mockito.when(cartService.findOrderItem(cartModel.getOrderItems(), 2L)).thenReturn(cartModel.getOrderItems().get(0));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/decrementItem")
                .sessionAttr("cart", cartModel)
                .param("itemId", "2")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("dash-board"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/dash-board.jsp"))
                .andExpect(MockMvcResultMatchers.model().attribute("cart", Matchers.hasProperty("totalPrice", Matchers.is(2000D))))
                .andExpect(MockMvcResultMatchers.model().attribute("cart", Matchers.hasProperty("orderItems", Matchers.hasItem(
                        Matchers.allOf(
                                Matchers.hasProperty("item", Matchers.allOf(
                                        Matchers.hasProperty("itemId", Matchers.is(2L)),
                                        Matchers.hasProperty("itemName", Matchers.is("dove")),
                                        Matchers.hasProperty("itemPrice", Matchers.is(2000D))
                                )),
                                Matchers.hasProperty("quantity", Matchers.is(1))
                        )
                ))));
    }

    @Test
    void testDecrementLessThanOneItem() throws Exception {

        CartModel cartModel = getCart();
        OrderItem orderItem = cartModel.getOrderItems().get(0);
        orderItem.setQuantity(1);
        cartModel = new CartModel();
        cartModel.addItem(orderItem);

        Mockito.when(cartService.findOrderItem(cartModel.getOrderItems(), 2L)).thenReturn(cartModel.getOrderItems().get(0));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/decrementItem")
                .sessionAttr("cart", cartModel)
                .param("itemId", "2")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("dash-board"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/dash-board.jsp"))
                .andExpect(MockMvcResultMatchers.model().attribute("cart", Matchers.hasProperty("totalPrice", Matchers.is(2000D))))
                .andExpect(MockMvcResultMatchers.model().attribute("cart", Matchers.hasProperty("orderItems", Matchers.hasItem(
                        Matchers.allOf(
                                Matchers.hasProperty("item", Matchers.allOf(
                                        Matchers.hasProperty("itemId", Matchers.is(2L)),
                                        Matchers.hasProperty("itemName", Matchers.is("dove")),
                                        Matchers.hasProperty("itemPrice", Matchers.is(2000D))
                                )),
                                Matchers.hasProperty("quantity", Matchers.is(1))
                        )
                ))));
    }

    @Test
    void testDeleteItem() throws Exception {

        CartModel cartModel = getCart();

        Mockito.when(cartService.findOrderItem(cartModel.getOrderItems(), 2L)).thenReturn(cartModel.getOrderItems().get(0));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/deleteItem")
                .sessionAttr("cart", cartModel)
                .param("itemId", "2")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("dash-board"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/view/dash-board.jsp"))
                .andExpect(MockMvcResultMatchers.model().attribute("cart", Matchers.hasProperty("totalPrice", Matchers.is(0D))))
                .andExpect(MockMvcResultMatchers.model().attribute("cart", Matchers.hasProperty("orderItems", Matchers.emptyIterable())));
    }

    @Test
    void testCartRedirection() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/addItem?itemId=2"))
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/dashboard"))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/dashboard"));
    }

}
