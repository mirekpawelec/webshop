/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.controller;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pawelec.webshop.model.Cart;
import pl.pawelec.webshop.service.CartService;

/**
 *
 * @author mirek
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    
    private Logger logger = Logger.getLogger(CartController.class);
    
    @Autowired
    private CartService cartService;
    
    @RequestMapping
    public String getSessionId(HttpServletRequest request){
        logger.info("### getSessionId, sessionId=" + request.getSession(true).getId());
        return "redirect:/cart/" + request.getSession(true).getId();
    } 
    
    @RequestMapping("/{cartId}")
    public String getCartBySessionId(@PathVariable String cartId, Model model, HttpServletRequest request){
//        model.addAttribute("sessionId", request.getSession(true).getId());
        model.addAttribute("jspFile", "cart");
        return "cart";
    } 
}
