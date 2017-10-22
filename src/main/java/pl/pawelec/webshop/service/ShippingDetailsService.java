/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service;

import java.util.List;
import pl.pawelec.webshop.model.ShippingDetails;

/**
 *
 * @author mirek
 */
public interface ShippingDetailsService {
    void create(ShippingDetails shippingDetails);
    void update(ShippingDetails shippingDetails);
    void delete(ShippingDetails shippingDetails);
    void deleteById(Long id);
    void deleteAll();
    ShippingDetails getOneById(Long id);
    List<ShippingDetails> getAll();
    Long count();
    boolean exists(Long id);
    ShippingDetails createAndReturn(ShippingDetails shippingDetails);
}
