/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.stereotype.Repository;
import pl.pawelec.webshop.exception.NoLoadunitNoException;
import pl.pawelec.webshop.model.DeliveryItem;
import pl.pawelec.webshop.model.dao.AbstrDao;
import pl.pawelec.webshop.model.dao.DeliveryItemDao;

/**
 *
 * @author mirek
 */
@Repository
public class DeliveryItemDaoImpl extends AbstrDao<DeliveryItem> implements DeliveryItemDao, Serializable{

    @Override
    public DeliveryItem getByLoadunitNo(String loadunitNo) {
        try{
            return (DeliveryItem) getEntityManager().createQuery("from DeliveryItem WHERE loadunit_no = :loadunit_no").setParameter("loadunit_no", loadunitNo).getSingleResult();
        } catch(NoResultException re){
            throw new NoLoadunitNoException(loadunitNo);
        }
    }

    @Override
    public List<DeliveryItem> getByDeliveryId(Long deliveryId) {
        return getEntityManager().createQuery("from DeliveryItem WHERE delivery_id = :delivery_id").setParameter("delivery_id", deliveryId).getResultList();
    }

    @Override
    public List<Object> getSummaryDelivery(Long id){
        List<Object> list = getEntityManager().createQuery("SELECT delivery.deliveryId, product.productNo, product.name, sum(quantity) "
                                                 + "FROM DeliveryItem WHERE delivery.deliveryId = :deliveryId "
                                                 + "GROUP BY delivery.deliveryId, product.productNo, product.name").setParameter("deliveryId", id).getResultList();
        return list;
    }
    
}
