package com.project.ecommerce.service;

import com.project.ecommerce.dto.item.Item;
import com.project.ecommerce.dto.order.Order;
import com.project.ecommerce.model.CartModel;
import java.util.List;

public interface UserService {
    List<Item> getItemsList();

    String getUsernameFromAuth();

    void placeOrder(CartModel cartModel);

    List<Order> getOrdersOfUser();
}
