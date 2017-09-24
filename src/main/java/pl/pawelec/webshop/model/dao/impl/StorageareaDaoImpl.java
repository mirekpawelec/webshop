/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao.impl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.stereotype.Repository;
import pl.pawelec.webshop.model.Storagearea;
import pl.pawelec.webshop.model.dao.AbstrDao;
import pl.pawelec.webshop.model.dao.StorageareaDao;

/**
 *
 * @author mirek
 */
@Repository
public class StorageareaDaoImpl extends AbstrDao<Storagearea> implements StorageareaDao{

    @Override
    public List<Storagearea> getByDescription(String wholeDescriptionOrPart) {
        return  getEntityManager().createQuery("FROM Storagearea WHERE description LIKE :description").setParameter("description", "%"+wholeDescriptionOrPart+"%").getResultList();
    }
    
}
