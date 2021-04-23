package com.project.ecommerce.service_implementation;

import com.project.ecommerce.dto.order.OrderItem;
import com.project.ecommerce.entity.order.OrderEntity;
import com.project.ecommerce.entity.user.UserEntity;
import com.project.ecommerce.model.CartModel;
import com.project.ecommerce.repository.ItemRepository;
import com.project.ecommerce.repository.OrderRepository;
import com.project.ecommerce.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@WithMockUser(username = "shaw", authorities = {"ROLE_USER"})
class UserServiceTest {
    @MockBean
    UserRepository userRepository;

    @MockBean
    ItemRepository itemRepository;

    @MockBean
    OrderRepository orderRepository;

    @Autowired
    UserServiceImplementation userService;

    @Test
    void testGetUsernameFromAuth(){
        Assertions.assertThat(userService.getUsernameFromAuth()).isEqualTo("shaw");
    }

    @Test
    void getItemList(){
        Mockito.when(itemRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertThat(userService.getItemsList()).isEmpty();
        Mockito.verify(itemRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testPlaceOrder(){
        CartModel cartModel = new CartModel();
        List<OrderItem> orderItems = new ArrayList<>();
        cartModel.setOrderItems(orderItems);
        userService.placeOrder(cartModel);
        Mockito.verify(userRepository, Mockito.times(1)).getOne(Mockito.anyString());
        Mockito.verify(orderRepository, Mockito.times(1)).save(Mockito.any(OrderEntity.class));
    }

    @Test
    void testGetOrderOfUser(){
        UserEntity userEntity = new UserEntity();
        userEntity.setOrderEntities(new ArrayList<>());
        Mockito.when(userRepository.getOne("shaw")).thenReturn(userEntity);
        userService.getOrdersOfUser();
        Mockito.verify(userRepository, Mockito.times(1)).getOne(Mockito.anyString());
    }
}
