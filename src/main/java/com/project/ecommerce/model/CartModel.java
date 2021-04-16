package com.project.ecommerce.model;

import com.project.ecommerce.dto.OrderItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CartModel {
    private List<OrderItem> orderItems;

    public CartModel(){
        this.orderItems = new ArrayList<>();
    }

    public void addItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
    }
}
