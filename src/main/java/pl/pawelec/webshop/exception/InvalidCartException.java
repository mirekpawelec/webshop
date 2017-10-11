/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.exception;

/**
 *
 * @author mirek
 */
public class InvalidCartException extends RuntimeException{
    private String sessionId;
    
    public InvalidCartException(String sessionId) {
        this.sessionId = sessionId;
    }
    
    public String getCartId() {
        return sessionId;
    }
}
