/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import pl.pawelec.webshop.service.DeliveryService;

/**
 *
 * @author mirek
 */
public class DeliveryOrder implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private Delivery delivery;
    private DeliveryItem item;
    private List<DeliveryItem> deliveryItems;
//
    public DeliveryOrder() {
        item = new DeliveryItem();
        deliveryItems = new ArrayList<DeliveryItem>();
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public DeliveryItem getItem() {
        return item;
    }

    public void setItem(DeliveryItem item) {
        this.item = item;
    }

    public List<DeliveryItem> getDeliveryItems() {
        return deliveryItems;
    }

    public void setDeliveryItems(List<DeliveryItem> deliveryItems) {
        this.deliveryItems = deliveryItems;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(deliveryItems.size() > 0){
            for(DeliveryItem di : deliveryItems){
                sb.append("[" + di + "] ");
            }
        } else {
            sb.append("[]");
        }
        return "DeliveryOrder{" + "delivery=" + delivery + "item=" + item + ", deliveryItems=" + sb.toString() + '}';
    }
    
}
