/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.converter;

import pl.pawelec.webshop.exception.*;

/**
 *
 * @author mirek
 */
public class CartNotFoundException extends RuntimeException{
    private String cartId;
    private String sessionId;

    public CartNotFoundException(String cartId, String sessionId) {
        this.cartId = cartId;
        this.sessionId = sessionId;
    }

    public String getCartId() {
        return cartId;
    }

    public String getSessionId() {
        return sessionId;
    }
   
}
