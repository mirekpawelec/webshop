/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service.impl;


import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pawelec.webshop.model.UserDetailsAdapter;
import pl.pawelec.webshop.model.UserInfo;
import pl.pawelec.webshop.model.dao.UserInfoDao;
import pl.pawelec.webshop.model.enum_.UserStatus;
import pl.pawelec.webshop.service.UserInfoService;

/**
 *
 * @author mirek
 */
@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService{

    @Autowired
    private UserInfoDao userInfoDao;
    
    Logger logger = Logger.getLogger(UserInfoServiceImpl.class);
    
    
    
    public void create(UserInfo userInfo) {
        userInfoDao.create(userInfo);
    }

    public void update(UserInfo userInfo) {
        userInfoDao.update(userInfo);
    }

    public void delete(UserInfo userInfo) {
        userInfoDao.delete(userInfo);
    }

    public void deleteById(Long id) {
        userInfoDao.deleteById(id);
    }

    public void deleteAll() {
        userInfoDao.deleteAll();
    }

    public UserInfo getOneById(Long id) {
        return userInfoDao.getOneById(id);
    }

    public List<UserInfo> getAll() {
        return userInfoDao.getAll();
    }

    public Long count() {
        return userInfoDao.count();
    }

    public boolean exists(Long id) {
        return userInfoDao.exists(id);
    }

    public UserInfo getByLogin(String userLogin){
        return userInfoDao.getByLogin(userLogin);
    }
    
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo activeUserInfo = userInfoDao.getByLogin(username, UserStatus.OK.name());
        if(activeUserInfo==null){
            logger.info("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        
        UserDetailsAdapter user = new UserDetailsAdapter(activeUserInfo);
        return user;
        
//        GrantedAuthority authority = new SimpleGrantedAuthority(activeUserInfo.getRole());
//        UserDetails userDetails = (UserDetails) new User(activeUserInfo.getLogin(), activeUserInfo.getPassword(), Arrays.asList(authority));
//        return userDetails;
        
    }
    
}
