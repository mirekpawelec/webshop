/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service;

import java.io.Serializable;
import java.util.List;
import pl.pawelec.webshop.model.Delivery;
import pl.pawelec.webshop.model.Storageplace;

/**
 *
 * @author mirek
 */
public interface DeliveryService {
    void create(Delivery delivery);
    void update(Delivery delivery);
    void delete(Delivery delivery);
    void deleteById(Long Id);
    void deleteAll();
    Delivery getOneById(Long Id);
    List<Delivery> getAll();
    Long count();
    boolean exists(Long Id);
    List<Delivery> getByDriver(String firstName, String lastName, String phoneNo);
    List<Delivery> getByTruck(String type, String truckNumber, String trailerOrCaravanNumber);
    Delivery startProcessDelivery(String deliveryId);
    String closeDelivery(Long Id);
    Delivery setPlaceIdIntoDelivery(Delivery delivery, List<Storageplace> storageplaces);
    boolean saveDetailsDelivery(Delivery delivery);
}
