/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.controller;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.pawelec.webshop.model.Order;
import pl.pawelec.webshop.model.UserInfo;
import pl.pawelec.webshop.service.OrderService;
import pl.pawelec.webshop.service.UserInfoService;
import pl.pawelec.webshop.utils.AtributesModel;

/**
 *
 * @author mirek
 */
@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserInfoService userInfoService;
    
    
    @RequestMapping("/admin")
    public String getAllOrders(Model model, HttpServletRequest request){
        model.addAttribute("orders", orderService.getAll());
        model.addAttribute("jspFile","orders");
        AtributesModel.addGlobalAtributeToModel(model, request);
        return "orders";
    }
    
    @RequestMapping("/admin/order/{id}")
    public String getByOrderId(@PathVariable("id") String orderId, Model model, HttpServletRequest request){
        Order order = orderService.getOneById(Long.valueOf(orderId));
        order.getCart().updateCostOfAllItems();
        model.addAttribute("order", order);
        model.addAttribute("jspFile","order");
        AtributesModel.addGlobalAtributeToModel(model, request);
        return "order";
    }
    
    @RequestMapping("/user/{login}")
    public String getCustomerOrdersByLogin(@PathVariable("login") String userLogin, Model model, HttpServletRequest request){
        List<Order> orderList = orderService.getByUserLogin(userLogin);
        orderList.forEach(o->o.getCart().updateCostOfAllItems());
        model.addAttribute("orders", orderList);
        model.addAttribute("user", userInfoService.getByLogin(userLogin));
        model.addAttribute("userOrdersList", true);
        model.addAttribute("jspFile","order");
        AtributesModel.addGlobalAtributeToModel(model, request);
        return "order";
    }
    
    @RequestMapping("/user/order/{id}")
    public String getCutomerOrderById(@PathVariable("id") String orderId, Model model, HttpServletRequest request){
        Order order = orderService.getOneById(Long.valueOf(orderId));
        order.getCart().updateCostOfAllItems();
        model.addAttribute("order", order);
        model.addAttribute("userOrder", true);
        model.addAttribute("jspFile","order");
        AtributesModel.addGlobalAtributeToModel(model, request);
        return "order";
    }
    
}
