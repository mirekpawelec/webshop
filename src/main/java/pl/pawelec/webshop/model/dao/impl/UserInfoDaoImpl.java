/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao.impl;

import javax.persistence.NoResultException;
import org.jboss.logging.Logger;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import pl.pawelec.webshop.model.UserInfo;
import pl.pawelec.webshop.model.dao.AbstrDao;
import pl.pawelec.webshop.model.dao.UserInfoDao;

/**
 *
 * @author mirek
 */
@Repository
public class UserInfoDaoImpl extends AbstrDao<UserInfo> implements UserInfoDao{

    Logger logger = Logger.getLogger(UserInfoDaoImpl.class);

    @Override
    public UserInfo getByLogin(String userLogin) {
        UserInfo userInfo = null;
        try{
            userInfo = (UserInfo) getEntityManager().createQuery("from UserInfo WHERE login = :login")
                        .setParameter("login", userLogin)
                        .getSingleResult();
        } catch (NoResultException nre){
            logger.info("The " + userLogin + " does not exist!");
        }
        return userInfo;
    }
    
    public UserInfo getByLogin(String userLogin, String status){
        UserInfo userInfo = null;
        try{
            userInfo = (UserInfo) getEntityManager().createQuery("from UserInfo WHERE login = :login AND status = :status")
                        .setParameter("login", userLogin)
                        .setParameter("status", status)
                        .getSingleResult();
        } catch (NoResultException nre){
            logger.info("For parameters " + userLogin + ", " + status + " no data was found!");
        }
        return userInfo;
    }
    
}
