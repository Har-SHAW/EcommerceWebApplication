package com.project.ecommerce.controller;

import com.project.ecommerce.binder.InitBinderClass;
import com.project.ecommerce.dto.order.OrderItem;
import com.project.ecommerce.model.CartModel;
import com.project.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping("/")
public class CartController extends InitBinderClass {

    @Autowired
    CartService cartService;

    @RequestMapping("/addItem")
    public String addItem(@RequestParam(name = "itemId") String itemId,
                          Model model,
                          HttpServletRequest request
    ){

        Long itemIdLong = Long.parseLong(itemId);
        CartModel cartModel = (CartModel) request.getSession().getAttribute("cart");

        model.addAttribute("items", cartService.getItemsList());

        if (cartService.containsOrderItem(cartModel.getOrderItems(), itemIdLong)) {
            return "dash-board";
        }
        cartModel.addItem(cartService.getOrderItem(Long.parseLong(itemId)));

        model.addAttribute("cart",cartModel);

        return "dash-board";
    }

    @RequestMapping("/incrementItem")
    public String incrementItem(@RequestParam(name = "itemId") String itemId,
                                Model model,
                                HttpServletRequest request){
        Long itemIdLong = Long.parseLong(itemId);
        CartModel cartModel = (CartModel) request.getSession().getAttribute("cart");

        OrderItem orderItem = cartService.findOrderItem(cartModel.getOrderItems(), itemIdLong);

        orderItem.setQuantity(orderItem.getQuantity() + 1);
        cartModel.setTotalPrice(cartModel.getTotalPrice() + orderItem.getItem().getItemPrice());

        model.addAttribute("cart", cartModel);
        model.addAttribute("items", cartService.getItemsList());

        return "dash-board";
    }

    @RequestMapping("/decrementItem")
    public String decrementItem(@RequestParam(name = "itemId") String itemId,
                                Model model,
                                HttpServletRequest request){
        Long itemIdLong = Long.parseLong(itemId);
        CartModel cartModel = (CartModel) request.getSession().getAttribute("cart");

        OrderItem orderItem = cartService.findOrderItem(cartModel.getOrderItems(), itemIdLong);

        if (orderItem.getQuantity() > 1) {
            orderItem.setQuantity(orderItem.getQuantity() - 1);
            cartModel.setTotalPrice(cartModel.getTotalPrice() - orderItem.getItem().getItemPrice());
        }

        model.addAttribute("cart", cartModel);
        model.addAttribute("items", cartService.getItemsList());

        return "dash-board";
    }

    @RequestMapping("/deleteItem")
    public String deleteItem(@RequestParam(name = "itemId") String itemId,
                             Model model,
                             HttpServletRequest request){
        Long itemIdLong = Long.parseLong(itemId);
        CartModel cartModel = (CartModel) request.getSession().getAttribute("cart");

        OrderItem orderItem = cartService.findOrderItem(cartModel.getOrderItems(), itemIdLong);

        cartModel.removeItem(orderItem);

        model.addAttribute("cart", cartModel);
        model.addAttribute("items", cartService.getItemsList());

        return "dash-board";
    }
}
