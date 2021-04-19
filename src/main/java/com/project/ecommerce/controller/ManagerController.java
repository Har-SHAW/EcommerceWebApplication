package com.project.ecommerce.controller;

import com.project.ecommerce.binder.InitBinderClass;
import com.project.ecommerce.dto.item.Item;
import com.project.ecommerce.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/managerDashboard")
public class ManagerController extends InitBinderClass {

    @Autowired
    ManagerService managerService;

    @RequestMapping("/showItems")
    public String showItems(Model model){
        model.addAttribute("items", managerService.getAllItems());
        model.addAttribute("item", new Item());
        return "manager-items";
    }

    @RequestMapping("/editItem")
    public String editItem(@RequestParam("itemId") String itemId, Model model){
        Item item = managerService.getItemById(Long.parseLong(itemId));
        model.addAttribute("item", item);
        return "edit-item";
    }

    @RequestMapping("/processEdit")
    public String processEdit(@Valid @ModelAttribute("item") Item item, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            return "edit-item";
        }

        managerService.editItem(item);

        model.addAttribute("items", managerService.getAllItems());
        model.addAttribute("item", new Item());
        return "manager-items";
    }

    @RequestMapping("/processAdd")
    public String processAdd(@Valid @ModelAttribute("item") Item item, BindingResult bindingResult, Model model){

        if (managerService.findByName(item.getItemName()) != null){
            bindingResult.rejectValue("itemName", "error.itemName", "Item already exist");
        }

        if (bindingResult.hasErrors()){
            model.addAttribute("items", managerService.getAllItems());
            return "manager-items";
        }

        managerService.addItem(item);

        model.addAttribute("item", new Item());
        model.addAttribute("items", managerService.getAllItems());
        return "manager-items";
    }

    @RequestMapping("/deleteItem")
    public String deleteItem(@RequestParam("itemId") String itemId, Model model){
        managerService.deleteItem(Long.parseLong(itemId));
        model.addAttribute("items", managerService.getAllItems());
        return "manager-items";
    }
}
