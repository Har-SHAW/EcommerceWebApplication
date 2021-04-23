package com.project.ecommerce.service;

import com.project.ecommerce.dto.item.Item;
import com.project.ecommerce.entity.item.ItemEntity;
import java.util.List;

public interface ManagerService {
    List<Item> getAllItems();

    Item getItemById(Long id);

    void editItem(Item item);

    ItemEntity findByName(String name);

    void addItem(Item item);

    void setInStock(Long id);

    void setNoStock(Long id);
}
