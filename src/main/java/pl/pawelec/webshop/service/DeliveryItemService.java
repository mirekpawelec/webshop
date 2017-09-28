/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service;

import java.util.List;
import pl.pawelec.webshop.model.DeliveryItem;


/**
 *
 * @author mirek
 */
public interface DeliveryItemService {
    void create(DeliveryItem deliveryItem);
    void update(DeliveryItem deliveryItem);
    void delete(DeliveryItem deliveryItem);
    void deleteById(Long Id);
    void deleteAll();
    DeliveryItem getOneById(Long Id);
    List<DeliveryItem> getAll();
    Long count();
    boolean exists(Long Id);
    DeliveryItem getByLoadunitNo(String loadunitNo);
    List<DeliveryItem> getByDeliveryId(Long deliveryId);
    DeliveryItem newDeliveryItem();
    String moveItemsToRepository(Long placeId, List<DeliveryItem> deliveryItems);
}
