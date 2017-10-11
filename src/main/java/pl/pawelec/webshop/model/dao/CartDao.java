/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao;

import java.util.List;
import pl.pawelec.webshop.model.Cart;

/**
 *
 * @author mirek
 */
public interface CartDao extends Dao<Cart>{
    Cart createAndGetCart(Cart cart);
    List<Cart> getBySessionId(String sessionId);
    boolean existsBySessionId(String sessionId, String status);
}
