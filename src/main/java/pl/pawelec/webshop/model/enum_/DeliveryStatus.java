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
public enum DeliveryStatus{
    OK("Utworzona"),
    RE("Realizacja"),
    FI("Zamknięta");

    private String description; 

    private DeliveryStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
