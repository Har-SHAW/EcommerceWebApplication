package com.project.ecommerce.controller;

import com.project.ecommerce.binder.InitBinderClass;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController extends InitBinderClass {

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

    @RequestMapping("/dashboard")
    public String getDashBoard(Model model,HttpServletRequest request){
        if (request.getSession().getAttribute("cart") == null){
            request.getSession().setAttribute("cart", new CartModel());
            model.addAttribute("cart", new CartModel());
        }else {
            model.addAttribute("cart", request.getSession().getAttribute("cart"));
        }

        model.addAttribute("items", getItemsList());

        return "dash-board";
    }

    @RequestMapping("/placeOrder")
    public String placeOrder(Model model,HttpServletRequest request){

        CartModel cartModel = (CartModel) request.getSession().getAttribute("cart");

        if (cartModel.getOrderItems().isEmpty()){
            model.addAttribute("items", getItemsList());
            return "dash-board";
        }

        OrderEntity orderEntity = new OrderEntity();

        List<OrderItem> orderItems = cartModel.getOrderItems();

        for (OrderItem orderItem : orderItems){
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            ItemEntity itemEntity = itemRepository.findById(orderItem.getItem().getItemId()).orElse(null);
            orderItemEntity.setItemEntity(itemEntity);
            orderItemEntity.setQuantity(orderItem.getQuantity());
            orderItemEntity.setOrderEntity(orderEntity);

            orderEntity.addOrderItem(orderItemEntity);
        }

        UserEntity userEntity = userRepository.findById(getUsernameFromAuth()).orElse(null);

        orderEntity.setUserEntity(userEntity);

        orderEntity.setOrderDate(LocalDateTime.now());

        orderRepository.save(orderEntity);

        return "success-order";
    }

    @RequestMapping("/showOrders")
    public String showOrders(Model model, HttpServletRequest request){
        UserEntity userEntity = userRepository.findById(getUsernameFromAuth()).orElse(null);
        assert userEntity != null;

        List<OrderEntity> orderEntities = userEntity.getOrderEntities();

        List<Order> orders = new ArrayList<>();

        for (OrderEntity orderEntity : orderEntities){
            orders.add(new Order(orderEntity));
        }

        model.addAttribute("orders", orders);
        return "user-orders";
    }

    @RequestMapping("/logout")
    public String logout(){
        SecurityContextHolder.getContext().setAuthentication(null);
        return "log-in";
    }
}
