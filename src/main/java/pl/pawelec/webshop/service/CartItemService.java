/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service;

import java.util.List;
import pl.pawelec.webshop.model.CartItem;

/**
 *
 * @author mirek
 */
public interface CartItemService {
    void create(CartItem cartItem);
    void update(CartItem cartItem);
    void delete(CartItem cartItem);
    void deleteById(Long id);
    void deleteAll();
    CartItem getOneById(Long id);
    List<CartItem> getAll();
    Long count();
    boolean exists(Long id);
}
