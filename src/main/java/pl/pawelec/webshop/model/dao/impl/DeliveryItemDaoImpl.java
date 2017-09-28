/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import pl.pawelec.webshop.model.DeliveryItem;
import pl.pawelec.webshop.model.dao.AbstrDao;
import pl.pawelec.webshop.model.dao.DeliveryItemDao;

/**
 *
 * @author mirek
 */
@Repository
public class DeliveryItemDaoImpl extends AbstrDao<DeliveryItem> implements DeliveryItemDao{

    @Override
    public DeliveryItem getByLoadunitNo(String loadunitNo) {
        return (DeliveryItem) getEntityManager().createQuery("from DeliveryItem WHERE loadunit_no = :loadunit_no").setParameter("loadunit_no", loadunitNo).getSingleResult();
    }

    @Override
    public List<DeliveryItem> getByDeliveryId(Long deliveryId) {
        return getEntityManager().createQuery("from DeliveryItem WHERE delivery_id = :delivery_id").setParameter("delivery_id", deliveryId).getResultList();
    }
    
}
