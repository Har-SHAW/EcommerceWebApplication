package com.project.ecommerce.repository;

import com.project.ecommerce.entity.item.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    ItemEntity findByItemName(String itemName);
}