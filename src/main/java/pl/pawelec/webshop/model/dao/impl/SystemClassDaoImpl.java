/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao.impl;

import javax.persistence.NoResultException;
import org.springframework.stereotype.Repository;
import pl.pawelec.webshop.exception.NoSystemClassKeyFoundException;
import pl.pawelec.webshop.model.SystemClass;
import pl.pawelec.webshop.model.dao.AbstrDao;
import pl.pawelec.webshop.model.dao.SystemClassDao;

/**
 *
 * @author mirek
 */
@Repository
public class SystemClassDaoImpl extends AbstrDao<SystemClass> implements SystemClassDao{
    
    public SystemClass getByUniqueKey(String symbol, String name) {
        try{
            return (SystemClass) getEntityManager().createQuery("from SystemClass WHERE symbol = :symbol AND name = :name")
                    .setParameter("symbol", symbol)
                    .setParameter("name", name)
                    .getSingleResult();
        }catch(NoResultException nre){
            throw new NoSystemClassKeyFoundException(symbol, name);
        }
    }
    
}
