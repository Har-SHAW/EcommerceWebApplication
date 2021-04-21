package com.project.ecommerce.repository;

import com.project.ecommerce.entity.order.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

}
