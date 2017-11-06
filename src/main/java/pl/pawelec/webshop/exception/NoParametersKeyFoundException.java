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
public class NoParametersKeyFoundException extends RuntimeException{
    private String symbol;
    private String name;

    public NoParametersKeyFoundException(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }
    
}
