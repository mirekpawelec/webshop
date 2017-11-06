/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao.impl;

import javax.persistence.NoResultException;
import org.springframework.stereotype.Repository;
import pl.pawelec.webshop.exception.NoParametersKeyFoundException;
import pl.pawelec.webshop.model.AppParameter;
import pl.pawelec.webshop.model.dao.AbstrDao;
import pl.pawelec.webshop.model.dao.AppParameterDao;

/**
 *
 * @author mirek
 */
@Repository
public class AppParameterDaoImpl extends AbstrDao<AppParameter> implements AppParameterDao{
    
    public AppParameter getByUniqueKey(String symbol, String name) {
        try{
            return (AppParameter) getEntityManager().createQuery("from AppParameter WHERE symbol = :symbol AND name = :name")
                    .setParameter("symbol", symbol)
                    .setParameter("name", name)
                    .getSingleResult();
        }catch(NoResultException nre){
            throw new NoParametersKeyFoundException(symbol, name);
        }
    }
    
}
