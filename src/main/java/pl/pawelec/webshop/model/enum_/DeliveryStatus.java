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
    OK("OK", "Utworzona"),
    RE("RE", "Realizacja"),
    FI("FI", "Zamknięta");

    private String status;
    private String description; 

    private DeliveryStatus(String status, String description) {
        this.status = status;
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "DeliveryStatus{" + "status=" + status + ", description=" + description + '}';
    }
    
}
