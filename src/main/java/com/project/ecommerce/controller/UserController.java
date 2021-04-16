package com.project.ecommerce.controller;

import com.project.ecommerce.dto.Item;
import com.project.ecommerce.dto.OrderItem;
import com.project.ecommerce.entity.item.ItemEntity;
import com.project.ecommerce.model.CartModel;
import com.project.ecommerce.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    public List<Item> getItemsList(){
        List<ItemEntity> itemEntities = itemRepository.findAll();

        List<Item> items = new ArrayList<>();
        for (ItemEntity itemEntity : itemEntities){
            items.add(new Item(itemEntity.getItemId(), itemEntity.getItemName(), itemEntity.getItemPrice()));
        }

        return items;
    }

    @RequestMapping("/dashboard")
    public String getDashBoard(Model model){
        model.addAttribute("cart", new CartModel());

        model.addAttribute("items", getItemsList());

        return "dash-board";
    }

    @RequestMapping("/addItem")
    public String addItem(
                          @RequestParam(name = "itemId") String itemId,
                          Model model
    ){


        ItemEntity itemEntity = itemRepository.getOne(Long.parseLong(itemId));
        Item item = new Item();
        item.copyFromEntity(itemEntity);

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setQuantity(1);

        CartModel cartModel = (CartModel) model.getAttribute("cart");
        assert cartModel != null;
        cartModel.addItem(orderItem);

        model.addAttribute("cart",cartModel);

        model.addAttribute("items", getItemsList());

        return "dash-board";
    }


}
