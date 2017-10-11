/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service;

import java.util.List;
import pl.pawelec.webshop.model.Cart;

/**
 *
 * @author mirek
 */
public interface CartService {
    void create(Cart cart);
    void update(Cart cart);
    void delete(Cart cart);
    void deleteById(Long id);
    void deleteAll();
    Cart getOneById(Long id);
    List<Cart> getAll();
    Long count();
    boolean exists(Long id);
    Cart createAndGetCart(Cart cart);
    List<Cart> getBySessionId(String sessionId);
    boolean existsBySessionId(String sessionId, String status);
}
