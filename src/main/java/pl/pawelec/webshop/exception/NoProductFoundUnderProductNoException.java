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
public class NoProductFoundUnderProductNoException extends RuntimeException{
    private String productNo;

    public NoProductFoundUnderProductNoException(String productNo) {
        this.productNo = productNo;
    }

    public String getProductNo() {
        return productNo;
    }

    @Override
    public String toString() {
        return "NoProductFoundUnderProductNoException{" + "productNo=" + productNo + '}';
    }
    
}
