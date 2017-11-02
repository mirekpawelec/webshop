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
public enum MessageStatus {

    NE("New"),
    RE("Read"),
    WT("Wait"),
    OK("Answered");
    
    private String description;

    private MessageStatus(String description) {
        this.description = description;
    }

    public String getName() {
        return this.name();
    }
        
    public String getDescription() {
        return description;
    }    
}
