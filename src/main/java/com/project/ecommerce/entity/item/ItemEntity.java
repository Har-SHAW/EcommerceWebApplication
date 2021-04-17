package com.project.ecommerce.entity.item;

import com.project.ecommerce.dto.item.Item;
import com.project.ecommerce.entity.order.OrderItemEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "items")
@Getter
@Setter
@NoArgsConstructor
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    private String itemName;
    private Double itemPrice;

    public void copyFromDTO(Item item){
        this.setItemName(item.getItemName());
        this.setItemId(item.getItemId());
        this.setItemPrice(item.getItemPrice());
    }
}
