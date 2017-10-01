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
public class NoProductIdFoundException extends RuntimeException{
    private Long productId;

    public NoProductIdFoundException(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }    
}
