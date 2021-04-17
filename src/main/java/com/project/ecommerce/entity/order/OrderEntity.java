package com.project.ecommerce.entity.order;

import com.project.ecommerce.entity.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL)
    private List<OrderItemEntity> orderItemEntities;

    @ManyToOne
    @JoinColumn(name = "username")
    private UserEntity userEntity;

    public void addOrderItem(OrderItemEntity orderItemEntity){
        if (this.orderItemEntities == null){
            this.orderItemEntities = new ArrayList<>();
        }

        this.orderItemEntities.add(orderItemEntity);
    }

    private String status;
}
