package com.project.ecommerce.service;

import com.project.ecommerce.dto.item.Item;
import com.project.ecommerce.dto.order.Order;
import com.project.ecommerce.dto.order.OrderItem;
import com.project.ecommerce.entity.item.ItemEntity;
import com.project.ecommerce.entity.order.OrderEntity;
import com.project.ecommerce.entity.order.OrderItemEntity;
import com.project.ecommerce.entity.user.UserEntity;
import com.project.ecommerce.model.CartModel;
import com.project.ecommerce.repository.ItemRepository;
import com.project.ecommerce.repository.OrderRepository;
import com.project.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    public List<Item> getItemsList(){
        List<ItemEntity> itemEntities = itemRepository.findAll();

        List<Item> items = new ArrayList<>();
        for (ItemEntity itemEntity : itemEntities){
            items.add(new Item(itemEntity));
        }

        return items;
    }

    public String getUsernameFromAuth(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        return username;
    }

    public void placeOrder(CartModel cartModel){
        var orderEntity = new OrderEntity();

        List<OrderItem> orderItems = cartModel.getOrderItems();

        for (OrderItem orderItem : orderItems){
            var orderItemEntity = new OrderItemEntity();
            var itemEntity = itemRepository.findById(orderItem.getItem().getItemId()).orElse(null);
            orderItemEntity.setItemEntity(itemEntity);
            orderItemEntity.setQuantity(orderItem.getQuantity());
            orderItemEntity.setOrderEntity(orderEntity);

            orderEntity.addOrderItem(orderItemEntity);
        }

        var userEntity = userRepository.findById(getUsernameFromAuth()).orElse(null);

        orderEntity.setUserEntity(userEntity);

        orderEntity.setOrderDate(LocalDateTime.now());

        orderRepository.save(orderEntity);
    }

    public List<Order> getOrdersOfUser(){
        var userEntity = userRepository.findById(getUsernameFromAuth()).orElse(null);
        assert userEntity != null;

        List<OrderEntity> orderEntities = userEntity.getOrderEntities();

        List<Order> orders = new ArrayList<>();

        for (var orderEntity : orderEntities){
            orders.add(new Order(orderEntity));
        }

        return orders;
    }
}
