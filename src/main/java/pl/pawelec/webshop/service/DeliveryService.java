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
    void deleteById(Long id);
    void deleteAll();
    Delivery getOneById(Long id);
    List<Delivery> getAll();
    Long count();
    boolean exists(Long id);
    List<Delivery> getByDriver(String firstName, String lastName, String phoneNo);
    List<Delivery> getByTruck(String type, String truckNumber, String trailerOrCaravanNumber);
    Delivery startProcessDelivery(String deliveryId);
    String closeDelivery(Long id);
    Delivery setPlaceIdAccordingToPlaceNo(Delivery delivery, List<Storageplace> storageplaces);
    String saveDetailsDelivery(Delivery delivery);
    void deleteByIdAndStatus(Long id, String status);
}
