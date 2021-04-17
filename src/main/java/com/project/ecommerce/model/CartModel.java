package com.project.ecommerce.model;

import com.project.ecommerce.dto.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CartModel {
    private List<OrderItem> orderItems;

    private Double totalPrice;

    public CartModel(){
        this.orderItems = new ArrayList<>();this.totalPrice = 0.0;
    }

    public void addItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
        this.totalPrice += orderItem.getItem().getItemPrice();
    }

    public void removeItem(OrderItem orderItem){
        this.orderItems.remove(orderItem);
        this.totalPrice -= orderItem.getItem().getItemPrice()*orderItem.getQuantity();
    }
}
