/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao;

import java.util.List;
import pl.pawelec.webshop.model.Delivery;

/**
 *
 * @author mirek
 */
public interface DeliveryDao extends Dao<Delivery>{
    List<Delivery> getByDriver(String firstName, String lastName, String phoneNo);
    List<Delivery> getByTruck(String type, String truckNumber, String trailerOrCaravanNumber);
    Long createAndGetId(Delivery entity);
    Delivery startProcessDelivery();
    boolean closeDelivery(Long id);
}
