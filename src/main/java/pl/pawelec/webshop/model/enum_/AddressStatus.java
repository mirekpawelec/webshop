/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.enum_;
/**
 *
 * @author mirek
 */
public enum AddressStatus {
    OK("OK"),
    BL("Blocked"),
    FI("Canceled");
    
    private String description;

    private AddressStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }   
}
