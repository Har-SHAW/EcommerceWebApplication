package com.project.ecommerce.dto;

import com.project.ecommerce.entity.order.OrderItemEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItem {
    private Integer quantity;
    private Item item;

    public OrderItem(OrderItemEntity orderItemEntity){
        this.quantity = orderItemEntity.getQuantity();
        this.item = new Item(orderItemEntity.getItemEntity());
    }
}
