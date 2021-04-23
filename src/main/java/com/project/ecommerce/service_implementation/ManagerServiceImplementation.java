package com.project.ecommerce.service_implementation;

import com.project.ecommerce.dto.item.Item;
import com.project.ecommerce.entity.item.ItemEntity;
import com.project.ecommerce.repository.ItemRepository;
import com.project.ecommerce.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerServiceImplementation implements ManagerService {


    @Autowired
    ItemRepository itemRepository;

    @Override
    public List<Item> getAllItems(){
        List<ItemEntity> itemEntities = itemRepository.findAll();
        List<Item> items = new ArrayList<>();

        for (ItemEntity itemEntity : itemEntities){
            items.add(new Item(itemEntity));
        }

        return items;
    }

    @Override
    public Item getItemById(Long id){
        return new Item(itemRepository.getOne(id));
    }

    @Override
    public void editItem(Item item){
        var itemEntity = itemRepository.getOne(item.getItemId());
        itemEntity.setItemName(item.getItemName());
        itemEntity.setItemPrice(item.getItemPrice());
        itemRepository.save(itemEntity);
    }

    @Override
    public ItemEntity findByName(String name){
        return itemRepository.findByItemName(name);
    }

    @Override
    public void addItem(Item item){
        var itemEntity = new ItemEntity();
        itemEntity.setItemName(item.getItemName());
        itemEntity.setItemPrice(item.getItemPrice());
        itemEntity.setIsOutOfStock(false);
        itemRepository.save(itemEntity);
    }

    @Override
    public void setInStock(Long id){
        var itemEntity = itemRepository.getOne(id);
        itemEntity.setIsOutOfStock(false);
        itemRepository.save(itemEntity);
    }

    @Override
    public void setNoStock(Long id){
        var itemEntity = itemRepository.getOne(id);
        itemEntity.setIsOutOfStock(true);
        itemRepository.save(itemEntity);
    }
}
