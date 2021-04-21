package com.project.ecommerce.service;

import com.project.ecommerce.dto.item.Item;
import com.project.ecommerce.dto.order.OrderItem;
import com.project.ecommerce.entity.item.ItemEntity;
import com.project.ecommerce.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    ItemRepository itemRepository;
    public boolean containsOrderItem(final List<OrderItem> list, final Long id){
        return list.stream().anyMatch(o -> o.getItem().getItemId().equals(id));
    }

    public OrderItem findOrderItem(final List<OrderItem> list, final Long id){
        return list.stream().filter(o -> o.getItem().getItemId().equals(id)).findFirst().orElse(null);
    }

    public List<Item> getItemsList(){
        List<ItemEntity> itemEntities = itemRepository.findAll();

        List<Item> items = new ArrayList<>();
        for (ItemEntity itemEntity : itemEntities){
            items.add(new Item(itemEntity));
        }

        return items;
    }

    public OrderItem getOrderItem(Long id){
        var itemEntity = itemRepository.getOne(id);
        var item = new Item(itemEntity);
        var orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setQuantity(1);
        return orderItem;
    }
}
