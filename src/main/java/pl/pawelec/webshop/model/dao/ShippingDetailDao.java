/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao;

import pl.pawelec.webshop.model.ShippingDetail;

/**
 *
 * @author mirek
 */
public interface ShippingDetailDao extends Dao<ShippingDetail>{
    ShippingDetail createAndReturn(ShippingDetail shippingDetail);
}
