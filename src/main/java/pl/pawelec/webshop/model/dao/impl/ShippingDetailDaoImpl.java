/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao.impl;

import org.springframework.stereotype.Repository;
import pl.pawelec.webshop.model.ShippingDetail;
import pl.pawelec.webshop.model.dao.AbstrDao;
import pl.pawelec.webshop.model.dao.ShippingDetailDao;

/**
 *
 * @author mirek
 */
@Repository
public class ShippingDetailDaoImpl extends AbstrDao<ShippingDetail> implements ShippingDetailDao{
    
    public ShippingDetail createAndReturn(ShippingDetail shippingDetail) {
        getEntityManager().persist(shippingDetail);
        return shippingDetail;
    }
    
}
