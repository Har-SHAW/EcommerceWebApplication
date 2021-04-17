package com.project.ecommerce.controller;

import com.project.ecommerce.dto.item.Item;
import com.project.ecommerce.dto.user.AdminUser;
import com.project.ecommerce.entity.item.ItemEntity;
import com.project.ecommerce.entity.user.UserEntity;
import com.project.ecommerce.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/managerDashboard")
public class ManagerController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmer);
    }

    @Autowired
    ItemRepository itemRepository;

    public List<Item> getAllItems(){
        List<ItemEntity> itemEntities = itemRepository.findAll();
        List<Item> items = new ArrayList<>();

        for (ItemEntity itemEntity : itemEntities){
            items.add(new Item(itemEntity));
        }

        return items;
    }

    @RequestMapping("/showItems")
    public String showItems(Model model){
        model.addAttribute("items", getAllItems());
        model.addAttribute("item", new Item());
        return "manager-items";
    }

    @RequestMapping("/editItem")
    public String editItem(@RequestParam("itemId") String itemId, Model model){
        Item item = new Item(Objects.requireNonNull(itemRepository.findById(Long.parseLong(itemId)).orElse(null)));
        model.addAttribute("item", item);
        return "edit-item";
    }

    @RequestMapping("/processEdit")
    public String processEdit(@Valid @ModelAttribute("item") Item item, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            return "edit-item";
        }

        ItemEntity itemEntity = itemRepository.findById(item.getItemId()).orElse(null);
        assert itemEntity != null;
        itemEntity.setItemName(item.getItemName());
        itemEntity.setItemPrice(item.getItemPrice());
        itemRepository.save(itemEntity);

        model.addAttribute("items", getAllItems());
        model.addAttribute("item", new Item());
        return "manager-items";
    }

    @RequestMapping("/processAdd")
    public String processAdd(@Valid @ModelAttribute("item") Item item, BindingResult bindingResult, Model model){

        if (itemRepository.findByItemName(item.getItemName()) != null){
            bindingResult.rejectValue("itemName", "error.itemName", "Item already exist");
        }


        if (bindingResult.hasErrors()){
            model.addAttribute("items", getAllItems());
            return "manager-items";
        }

        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setItemName(item.getItemName());
        itemEntity.setItemPrice(item.getItemPrice());
        itemRepository.save(itemEntity);

        model.addAttribute("item", new Item());
        model.addAttribute("items", getAllItems());
        return "manager-items";
    }

    @RequestMapping("/deleteItem")
    public String deleteItem(@RequestParam("itemId") String itemId, Model model){
        ItemEntity itemEntity = itemRepository.findById(Long.parseLong(itemId)).orElse(null);
        assert itemEntity != null;
        itemRepository.delete(itemEntity);
        model.addAttribute("items", getAllItems());
        return "manager-items";
    }
}
