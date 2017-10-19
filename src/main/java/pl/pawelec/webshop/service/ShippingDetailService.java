/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service;

import java.util.List;
import pl.pawelec.webshop.model.ShippingDetail;

/**
 *
 * @author mirek
 */
public interface ShippingDetailService {
    void create(ShippingDetail shippingDetail);
    void update(ShippingDetail shippingDetail);
    void delete(ShippingDetail shippingDetail);
    void deleteById(Long id);
    void deleteAll();
    ShippingDetail getOneById(Long id);
    List<ShippingDetail> getAll();
    Long count();
    boolean exists(Long id);
    ShippingDetail createAndReturn(ShippingDetail shippingDetail);
}
