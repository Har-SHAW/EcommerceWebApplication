package com.project.ecommerce.controller;

import com.project.ecommerce.dto.Item;
import com.project.ecommerce.dto.OrderItem;
import com.project.ecommerce.entity.item.ItemEntity;
import com.project.ecommerce.entity.order.OrderEntity;
import com.project.ecommerce.entity.order.OrderItemEntity;
import com.project.ecommerce.entity.user.UserEntity;
import com.project.ecommerce.model.CartModel;
import com.project.ecommerce.repository.ItemRepository;
import com.project.ecommerce.repository.OrderRepository;
import com.project.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmer);
    }

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
            items.add(new Item(itemEntity.getItemId(), itemEntity.getItemName(), itemEntity.getItemPrice()));
        }

        return items;
    }

    @RequestMapping("/dashboard")
    public String getDashBoard(Model model,HttpServletRequest request){
        request.getSession().setAttribute("cart", new CartModel());
        model.addAttribute("cart", new CartModel());

        model.addAttribute("items", getItemsList());

        return "dash-board";
    }

    @RequestMapping("/placeOrder")
    public String placeOrder(Model model,HttpServletRequest request){

        CartModel cartModel = (CartModel) request.getSession().getAttribute("cart");

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

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        UserEntity userEntity = userRepository.findById(username).orElse(null);

        orderEntity.setUserEntity(userEntity);

        orderRepository.save(orderEntity);

        return "success-order";
    }
}
