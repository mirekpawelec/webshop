/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import pl.pawelec.webshop.model.UserInfo;

/**
 *
 * @author mirek
 */
public interface UserInfoService extends UserDetailsService{
    void create(UserInfo userInfo);
    void update(UserInfo userInfo);
    void delete(UserInfo userInfo);
    void deleteById(Long id);
    void deleteAll();
    UserInfo getOneById(Long id);
    List<UserInfo> getAll();
    Long count();
    boolean exists(Long id);
    UserInfo getByLogin(String userLogin);
}
