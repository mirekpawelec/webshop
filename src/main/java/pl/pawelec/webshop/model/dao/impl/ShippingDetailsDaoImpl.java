/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao.impl;

import org.springframework.stereotype.Repository;
import pl.pawelec.webshop.model.ShippingDetails;
import pl.pawelec.webshop.model.dao.AbstrDao;
import pl.pawelec.webshop.model.dao.ShippingDetailsDao;

/**
 *
 * @author mirek
 */
@Repository
public class ShippingDetailsDaoImpl extends AbstrDao<ShippingDetails> implements ShippingDetailsDao{

    @Override
    public ShippingDetails createAndReturn(ShippingDetails shippingDetails) {
        getEntityManager().persist(shippingDetails);
        return shippingDetails;
    }
}
