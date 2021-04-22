package com.project.ecommerce.service;

import com.project.ecommerce.dto.item.Item;
import com.project.ecommerce.entity.item.ItemEntity;
import com.project.ecommerce.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

@SpringBootTest
class ManagerServiceTest {

    @MockBean
    ItemRepository itemRepository;

    @Autowired
    ManagerService managerService;

    @Test
    void testGetAll(){
        Mockito.when(itemRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertThat(managerService.getAllItems()).isEmpty();
    }

    @Test
    void testItemGetById(){
        Mockito.when(itemRepository.getOne(1L)).thenReturn(new ItemEntity());
        Assertions.assertThat(managerService.getItemById(1L)).isNotNull();
    }

    @Test
    void testEditItem(){
        Mockito.when(itemRepository.getOne(1L)).thenReturn(new ItemEntity());
        Item item = new Item();
        item.setItemPrice(100D);
        item.setItemName("dove");
        item.setItemId(1L);
        managerService.editItem(item);
        Mockito.verify(itemRepository, Mockito.times(1)).save(Mockito.any(ItemEntity.class));
        Mockito.verify(itemRepository, Mockito.times(1)).getOne(1L);
    }

    @Test
    void testFndByName(){
        Mockito.when(itemRepository.findByItemName("shaw")).thenReturn(new ItemEntity());
        Assertions.assertThat(managerService.findByName("shaw")).isNotNull();
    }

    @Test
    void testAddItem(){
        managerService.addItem(new Item());
        Mockito.verify(itemRepository, Mockito.times(1)).save(Mockito.any(ItemEntity.class));
    }

    @Test
    void testDeleteItem(){
        managerService.deleteItem(1L);
        Mockito.verify(itemRepository, Mockito.times(1)).deleteById(1L);
    }
}
