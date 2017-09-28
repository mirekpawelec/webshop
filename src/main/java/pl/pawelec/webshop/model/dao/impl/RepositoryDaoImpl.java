/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
    public List<Repository> getByStatus(String status) {
        return getEntityManager().createQuery("from Repository WHERE status = :status").setParameter("status", status).getResultList();
    }
    
    @Override
    public Repository getByLoadunitNo(String loadunitNo) {
        return (Repository) getEntityManager().createQuery("from Repository WHERE loadunit_no = :loadunit_no").setParameter("loadunit_no", loadunitNo).getSingleResult();
    }
    
}
