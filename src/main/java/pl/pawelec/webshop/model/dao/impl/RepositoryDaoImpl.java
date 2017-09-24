/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao.impl;

import pl.pawelec.webshop.model.Repository;
import pl.pawelec.webshop.model.dao.AbstrDao;
import pl.pawelec.webshop.model.dao.RepositoryDao;

/**
 *
 * @author mirek
 */
@org.springframework.stereotype.Repository
public class RepositoryDaoImpl extends AbstrDao<Repository> implements RepositoryDao{

    @Override
    public Repository getByLoadunitNo(String loadunitNo) {
        return (Repository) getEntityManager().createQuery("from Repository WHERE loadunit_no = :loadunit_no").setParameter("loadunit_no", loadunitNo).getSingleResult();
    }
    
}
