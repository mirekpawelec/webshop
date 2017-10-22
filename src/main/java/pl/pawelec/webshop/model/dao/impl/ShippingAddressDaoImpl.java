/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao.impl;

import org.springframework.stereotype.Repository;
import pl.pawelec.webshop.model.ShippingAddress;
import pl.pawelec.webshop.model.dao.AbstrDao;
import pl.pawelec.webshop.model.dao.ShippingAddressDao;

/**
 *
 * @author mirek
 */
@Repository
public class ShippingAddressDaoImpl extends AbstrDao<ShippingAddress> implements ShippingAddressDao{
    
    public ShippingAddress createAndReturn(ShippingAddress shippingAddress) {
        getEntityManager().persist(shippingAddress);
        return shippingAddress;
    }
    
}
