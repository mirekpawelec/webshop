/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.springframework.stereotype.Repository;
import pl.pawelec.webshop.model.Product;
import pl.pawelec.webshop.model.dao.AbstrDao;
import pl.pawelec.webshop.model.dao.ProductDao;

/**
 *
 * @author mirek
 */
@Repository
public class ProductDaoImpl extends AbstrDao<Product> implements ProductDao{

    @Override
    public List<Product> getByUnitsPrice(Double minPrice, Double maxPrice) {
        return getEntityManager().createQuery("from Product where unitPrice between :minPrice and :maxPrice")
                                .setParameter("minPrice", minPrice)
                                .setParameter("maxPrice", maxPrice)
                                .getResultList();
    }

    @Override
    public void delete(Product entity) {
        EntityManager em = getEntityManager();
        Product product = em.find(Product.class, entity.getProductId());
        if(product==null) 
           throw new RuntimeException("Operazja zakończyła się niepowodzeniem. Brak danych!");
        em.remove(product);
    }
}
