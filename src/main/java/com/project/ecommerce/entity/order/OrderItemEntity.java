package com.project.ecommerce.entity.order;

import com.project.ecommerce.entity.item.ItemEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@NoArgsConstructor
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemEntity itemEntity;
}