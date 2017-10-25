/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.controller;

import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.pawelec.webshop.exception.InvalidCartException;
import pl.pawelec.webshop.exception.NoProductIdFoundException;
import pl.pawelec.webshop.model.Cart;
import pl.pawelec.webshop.model.CartItem;
import pl.pawelec.webshop.model.Product;
import pl.pawelec.webshop.model.enum_.CartStatus;
import pl.pawelec.webshop.service.CartItemService;
import pl.pawelec.webshop.service.CartService;
import pl.pawelec.webshop.service.ProductService;

/**
 *
 * @author mirek
 */
@Controller
@RequestMapping("rest/cart")
public class CartRestController {
    private static int DEFAULT_QUANTITY = 1;
    Logger logger = Logger.getLogger(CartRestController.class);
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;
    @Autowired    
    private CartItemService cartItemService;
    
    
    @RequestMapping(value = "/{sessionId}", method = RequestMethod.POST)
    public @ResponseBody Cart create(@PathVariable String sessionId){
        logger.info("### create {sessionId="+sessionId+'}');
        return cartService.createAndGetCart(new Cart(sessionId));
    }
    
    @RequestMapping(value = "/{sessionId}", method = RequestMethod.GET)
    public @ResponseBody Cart read(@PathVariable String sessionId){
        logger.info("### read {sessionId="+sessionId+'}');
        Cart cart = cartService.getBySessionId(sessionId).stream().filter(ci->ci.getStatus().equals(CartStatus.RE.name()))
                .sorted((o1, o2) -> o1.getCreateDate().compareTo(o2.getCreateDate())).findFirst().orElse( new Cart(sessionId) );
        cart.updateCostOfAllItems();
        return cart;
    }
    
    @RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String cartId){
        logger.info("### delete {cartId="+cartId+'}');
        cartService.deleteById(Long.valueOf(cartId));
    }
    
    @RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addItemToCart(@PathVariable String productId, HttpServletRequest request){
        logger.info("### addItemToCart {productId="+productId+" , sessionId="+request.getSession(true).getId()+'}');
        Cart cart = null;
        Product addingProduct = null;
        CartItem cartItem = null;
        String sessionId = request.getSession(true).getId();
        if(cartService.existsBySessionId(sessionId, CartStatus.RE.name())){
            cart = getCurrentCart(sessionId, CartStatus.RE.name());
        } else {
            cart = cartService.createAndGetCart(new Cart(sessionId));
        }
        try{
            addingProduct = productService.getOneById(Long.valueOf(productId));
        }catch(NoProductIdFoundException npi){
            throw new IllegalArgumentException(String.format("The product about ID %d is not exists!", npi.getProductId()));
        }
        cartItem = cart.getCartItemSet().stream().filter(ci->ci.getProduct().getProductId().equals(Long.valueOf(productId))).findFirst().orElse(new CartItem(addingProduct));
        if(cartItem.getId()==null){
            cartItem.getCart().setCartId(cart.getCartId());
            cartItemService.create(cartItem);
        }else{
            cartItem.setLastModificationDate(LocalDateTime.now());
            cartItem.setQuantity( cartItem.getQuantity() + DEFAULT_QUANTITY);
            cartItem.updateTotalPrice();
            cartItemService.update(cartItem);
        }
    }
    
    @RequestMapping(value = "/delete/{productId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteItemFromCart(@PathVariable String productId, HttpServletRequest request){
        logger.info("### deleteItemFromCart {productId="+productId+" , sessionId="+request.getSession(true).getId()+'}');
        Cart cart = null;
        String sessionId = request.getSession(true).getId();
        if(cartService.existsBySessionId(sessionId, CartStatus.RE.name())){
            cart = getCurrentCart(sessionId, CartStatus.RE.name());
        } else {
            throw new InvalidCartException(sessionId);
        }
        CartItem deletingCartItem = cart.getCartItemSet().stream().filter(ci->ci.getProduct().getProductId().equals(Long.valueOf(productId))).findFirst().orElse(null);
        if(deletingCartItem!=null){
            cartItemService.delete(deletingCartItem);
        } else {
            throw new IllegalArgumentException(String.format("The cart does not containt the product about ID %s !", productId));
        }
    }
    
    @RequestMapping(value = "/items/{sessionId}", method = RequestMethod.GET)
    public @ResponseBody String getNumberOfItemsFromCart(@PathVariable String sessionId){
        Cart cart = cartService.getBySessionId(sessionId).stream().filter(c->c.getStatus().equals(CartStatus.RE.name())).findFirst().orElse( new Cart(sessionId) );
        return cart.getCartItemSet().size() + "";
    }
    
    private Cart getCurrentCart(String sessionId, String status){
        return cartService.getBySessionId(sessionId).stream().filter(c->c.getStatus().equals(status)).findFirst().get();
    }
}
