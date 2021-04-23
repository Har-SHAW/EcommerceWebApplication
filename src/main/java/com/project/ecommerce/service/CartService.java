package com.project.ecommerce.service;

import com.project.ecommerce.dto.item.Item;
import com.project.ecommerce.dto.order.OrderItem;
import java.util.List;

public interface CartService {
    boolean containsOrderItem(final List<OrderItem> list, final Long id);

    OrderItem findOrderItem(final List<OrderItem> list, final Long id);

    List<Item> getItemsList();

    OrderItem getOrderItem(Long id);
}
