package com.project.ecommerce.service;

import com.project.ecommerce.dto.item.Item;
import com.project.ecommerce.dto.order.OrderItem;
import com.project.ecommerce.entity.item.ItemEntity;
import com.project.ecommerce.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CartServiceTest {

    @MockBean
    ItemRepository itemRepository;

    @Autowired
    CartService cartService;

    @Test
    void testContains(){
        Assertions.assertThat(cartService.containsOrderItem(new ArrayList<>(), 1L)).isFalse();
        Assertions.assertThat(cartService.findOrderItem(new ArrayList<>(), 1L)).isNull();
    }

    @Test
    void testValues(){
        List<OrderItem> orderItemList = new ArrayList<>();
        Item item = new Item();
        item.setItemName("dove");
        item.setItemPrice(1000D);
        item.setItemId(1L);
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setQuantity(1);
        orderItemList.add(orderItem);
        Assertions.assertThat(cartService.containsOrderItem(orderItemList, 1L)).isTrue();
        Assertions.assertThat(cartService.findOrderItem(orderItemList, 1L)).isNotNull();
    }

    @Test
    void testGetOrderItem(){
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setItemId(1L);
        itemEntity.setItemName("dove");
        itemEntity.setItemPrice(1000D);
        Mockito.when(itemRepository.getOne(1L)).thenReturn(itemEntity);

        OrderItem orderItem = new OrderItem();
        Item item = new Item(itemEntity);
        orderItem.setItem(item);
        orderItem.setQuantity(1);

        OrderItem result = cartService.getOrderItem(1L);

        Assertions.assertThat(result.getItem().getItemName()).isEqualTo(item.getItemName());
        Assertions.assertThat(result.getItem().getItemId()).isEqualTo(item.getItemId());
        Assertions.assertThat(result.getItem().getItemPrice()).isEqualTo(item.getItemPrice());
        Assertions.assertThat(result.getQuantity()).isEqualTo(1);

        Mockito.verify(itemRepository, Mockito.times(1)).getOne(1L);
    }

    @Test
    void getAll(){
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setItemId(1L);
        itemEntity.setItemName("dove");
        itemEntity.setItemPrice(1000D);
        List<ItemEntity> itemEntities = new ArrayList<>();
        itemEntities.add(itemEntity);
        Mockito.when(itemRepository.findAll()).thenReturn(itemEntities);

        Assertions.assertThat(cartService.getItemsList()).isNotEmpty();

        Mockito.verify(itemRepository, Mockito.times(1)).findAll();
    }
}
