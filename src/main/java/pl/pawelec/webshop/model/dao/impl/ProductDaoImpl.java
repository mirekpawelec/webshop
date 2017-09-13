/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.pawelec.webshop.exception.NoProductFoundUnderProductNoException;
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

    @Override
    public Product getOneByProductNo(String productNo){
        Product product = null;
        try{
            product = (Product) getEntityManager().createQuery("from Product where product_no = :product_no").setParameter("product_no", productNo).getSingleResult();
        } catch(NoResultException re){
            throw new NoProductFoundUnderProductNoException(productNo);
        }
        return product;
    }
}
