package com.project.ecommerce.controller;

import com.project.ecommerce.binder.InitBinderClass;
import com.project.ecommerce.jsp_pages.JspPages;
import com.project.ecommerce.model.CartModel;
import com.project.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class UserController extends InitBinderClass {

    @Autowired
    UserService userService;

    @RequestMapping("/dashboard")
    public String getDashBoard(Model model,HttpServletRequest request){
        if (request.getSession().getAttribute("cart") == null){
            request.getSession().setAttribute("cart", new CartModel());
            model.addAttribute("cart", new CartModel());
        }else {
            model.addAttribute("cart", request.getSession().getAttribute("cart"));
        }

        model.addAttribute("items", userService.getItemsList());

        return JspPages.DASH_BOARD;
    }

    @RequestMapping("/placeOrder")
    public String placeOrder(Model model,HttpServletRequest request){

        CartModel cartModel = (CartModel) request.getSession().getAttribute("cart");

        if (cartModel.getOrderItems().isEmpty()){
            model.addAttribute("items", userService.getItemsList());
            return JspPages.DASH_BOARD;
        }

        userService.placeOrder(cartModel);

        return JspPages.SUCCESS_ORDER;
    }

    @RequestMapping("/showOrders")
    public String showOrders(Model model, HttpServletRequest request){

        model.addAttribute("orders", userService.getOrdersOfUser());
        return JspPages.USER_ORDERS;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().setAttribute("cart", null);
        SecurityContextHolder.getContext().setAuthentication(null);
        return JspPages.LOG_IN;
    }
}
