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
public class NoProductInCartException extends RuntimeException{
    private String productId;

    public NoProductInCartException(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }
}
