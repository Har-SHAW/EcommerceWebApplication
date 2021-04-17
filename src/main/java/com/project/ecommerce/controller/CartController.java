package com.project.ecommerce.controller;

import com.project.ecommerce.dto.Item;
import com.project.ecommerce.dto.OrderItem;
import com.project.ecommerce.entity.item.ItemEntity;
import com.project.ecommerce.model.CartModel;
import com.project.ecommerce.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class CartController {

    @Autowired
    ItemRepository itemRepository;

    public List<Item> getItemsList(){
        List<ItemEntity> itemEntities = itemRepository.findAll();

        List<Item> items = new ArrayList<>();
        for (ItemEntity itemEntity : itemEntities){
            items.add(new Item(itemEntity.getItemId(), itemEntity.getItemName(), itemEntity.getItemPrice()));
        }

        return items;
    }

    public boolean containsOrderItem(final List<OrderItem> list, final Long id){
        return list.stream().anyMatch(o -> o.getItem().getItemId().equals(id));
    }

    public OrderItem findOrderItem(final List<OrderItem> list, final Long id){
        return list.stream().filter(o -> o.getItem().getItemId().equals(id)).findFirst().orElse(null);
    }

    @RequestMapping("/addItem")
    public String addItem(@RequestParam(name = "itemId") String itemId,
                          Model model,
                          HttpServletRequest request
    ){

        Long itemIdLong = Long.parseLong(itemId);
        CartModel cartModel = (CartModel) request.getSession().getAttribute("cart");

        model.addAttribute("items", getItemsList());

        if (containsOrderItem(cartModel.getOrderItems(), itemIdLong)) {
            return "dash-board";
        }

        ItemEntity itemEntity = itemRepository.getOne(Long.parseLong(itemId));
        Item item = new Item(itemEntity);

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setQuantity(1);
        cartModel.addItem(orderItem);

        model.addAttribute("cart",cartModel);

        return "dash-board";
    }

    @RequestMapping("/incrementItem")
    public String incrementItem(@RequestParam(name = "itemId") String itemId,
                                Model model,
                                HttpServletRequest request){
        Long itemIdLong = Long.parseLong(itemId);
        CartModel cartModel = (CartModel) request.getSession().getAttribute("cart");

        OrderItem orderItem = findOrderItem(cartModel.getOrderItems(), itemIdLong);

        orderItem.setQuantity(orderItem.getQuantity() + 1);
        cartModel.setTotalPrice(cartModel.getTotalPrice() + orderItem.getItem().getItemPrice());

        model.addAttribute("cart", cartModel);
        model.addAttribute("items", getItemsList());

        return "dash-board";
    }

    @RequestMapping("/decrementItem")
    public String decrementItem(@RequestParam(name = "itemId") String itemId,
                                Model model,
                                HttpServletRequest request){
        Long itemIdLong = Long.parseLong(itemId);
        CartModel cartModel = (CartModel) request.getSession().getAttribute("cart");

        OrderItem orderItem = findOrderItem(cartModel.getOrderItems(), itemIdLong);

        if (orderItem.getQuantity() > 1) {
            orderItem.setQuantity(orderItem.getQuantity() - 1);
            cartModel.setTotalPrice(cartModel.getTotalPrice() - orderItem.getItem().getItemPrice());
        }

        model.addAttribute("cart", cartModel);
        model.addAttribute("items", getItemsList());

        return "dash-board";
    }

    @RequestMapping("/deleteItem")
    public String deleteItem(@RequestParam(name = "itemId") String itemId,
                             Model model,
                             HttpServletRequest request){
        Long itemIdLong = Long.parseLong(itemId);
        CartModel cartModel = (CartModel) request.getSession().getAttribute("cart");

        OrderItem orderItem = findOrderItem(cartModel.getOrderItems(), itemIdLong);

        cartModel.removeItem(orderItem);

        model.addAttribute("cart", cartModel);
        model.addAttribute("items", getItemsList());

        return "dash-board";
    }
}
