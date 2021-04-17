package com.project.ecommerce.dto.order;

import com.project.ecommerce.entity.order.OrderEntity;
import com.project.ecommerce.entity.order.OrderItemEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Order {
    private Long orderId;
    private LocalDateTime orderDate;
    private List<OrderItem> orderItems;

    public Order(OrderEntity orderEntity){
        this.orderId = orderEntity.getOrderId();
        this.orderDate = orderEntity.getOrderDate();
        if (this.orderItems == null){
            this.orderItems = new ArrayList<>();
        }
        for (OrderItemEntity orderItemEntity : orderEntity.getOrderItemEntities()){
            this.orderItems.add(new OrderItem(orderItemEntity));
        }
    }
}
