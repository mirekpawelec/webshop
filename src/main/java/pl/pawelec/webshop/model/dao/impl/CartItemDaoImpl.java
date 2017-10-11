/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao.impl;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import pl.pawelec.webshop.model.CartItem;
import pl.pawelec.webshop.model.dao.AbstrDao;
import pl.pawelec.webshop.model.dao.CartItemDao;

/**
 *
 * @author mirek
 */
@Repository
public class CartItemDaoImpl extends AbstrDao<CartItem> implements CartItemDao{

    Logger logger = Logger.getLogger(CartItemDaoImpl.class);
     
    @Override
    public void delete(CartItem entity) {
        try{
            if(super.exists(entity.getId())){
                super.delete(entity);
            } else {
                logger.info("The object does not exists!");
            }
        } catch(NullPointerException ne){
            logger.info("The argument passed is empty!");
        }
    }
}
