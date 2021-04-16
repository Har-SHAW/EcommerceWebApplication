package com.project.ecommerce.entity.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime orderDate;

    @OneToMany
    @JoinColumn(name = "order_entity_id")
    private List<OrderItemEntity> orderItemEntities;
}
