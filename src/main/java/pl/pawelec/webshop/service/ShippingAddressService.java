/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service;

import java.util.List;
import pl.pawelec.webshop.model.ShippingAddress;

/**
 *
 * @author mirek
 */
public interface ShippingAddressService {
    void create(ShippingAddress shippingAddress);
    void update(ShippingAddress shippingAddress);
    void delete(ShippingAddress shippingAddress);
    void deleteById(Long id);
    void deleteAll();
    ShippingAddress getOneById(Long id);
    List<ShippingAddress> getAll();
    Long count();
    boolean exists(Long id);
    ShippingAddress createAndReturn(ShippingAddress shippingAddress);
}
