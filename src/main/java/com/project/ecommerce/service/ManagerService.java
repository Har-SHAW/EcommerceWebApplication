package com.project.ecommerce.service;

import com.project.ecommerce.dto.item.Item;
import com.project.ecommerce.entity.item.ItemEntity;
import com.project.ecommerce.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerService {


    @Autowired
    ItemRepository itemRepository;

    public List<Item> getAllItems(){
        List<ItemEntity> itemEntities = itemRepository.findAll();
        List<Item> items = new ArrayList<>();

        for (ItemEntity itemEntity : itemEntities){
            items.add(new Item(itemEntity));
        }

        return items;
    }

    public Item getItemById(Long id){
        return new Item(itemRepository.getOne(id));
    }

    public void editItem(Item item){
        var itemEntity = itemRepository.getOne(item.getItemId());
        itemEntity.setItemName(item.getItemName());
        itemEntity.setItemPrice(item.getItemPrice());
        itemRepository.save(itemEntity);
    }

    public ItemEntity findByName(String name){
        return itemRepository.findByItemName(name);
    }

    public void addItem(Item item){
        var itemEntity = new ItemEntity();
        itemEntity.setItemName(item.getItemName());
        itemEntity.setItemPrice(item.getItemPrice());
        itemRepository.save(itemEntity);
    }

    public void deleteItem(Long id){
        itemRepository.deleteById(id);
    }
}
