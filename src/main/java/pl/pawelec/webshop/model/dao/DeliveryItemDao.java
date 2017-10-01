/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao;

import java.util.List;
import pl.pawelec.webshop.model.DeliveryItem;

/**
 *
 * @author mirek
 */
public interface DeliveryItemDao extends Dao<DeliveryItem>{
    DeliveryItem getByLoadunitNo(String loadunitNo);
    List<DeliveryItem> getByDeliveryId(Long deliveryId);
    List<Object> getSummaryDelivery(Long id);
}
