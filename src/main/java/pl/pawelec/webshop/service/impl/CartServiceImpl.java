/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pawelec.webshop.model.Cart;
import pl.pawelec.webshop.model.dao.CartDao;
import pl.pawelec.webshop.service.CartService;

/**
 *
 * @author mirek
 */
@Service
@Transactional
public class CartServiceImpl implements CartService{

    @Autowired
    private CartDao cartDao;
    
    @Override
    public void create(Cart cart) {
        cartDao.create(cart);
    }

    @Override
    public void update(Cart cart) {
        cartDao.update(cart);
    }

    @Override
    public void delete(Cart cart) {
        cartDao.delete(cart);
    }

    @Override
    public void deleteById(Long id) {
        cartDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        cartDao.deleteAll();
    }

    @Override
    public Cart getOneById(Long id) {
        return cartDao.getOneById(id);
    }

    @Override
    public List<Cart> getAll() {
        return cartDao.getAll();
    }

    @Override
    public Long count() {
        return cartDao.count();
    }

    @Override
    public boolean exists(Long id) {
        return cartDao.exists(id);
    }
    
    @Override
    public Cart createAndGetCart(Cart cart) {
        return cartDao.createAndGetCart(cart);
    }
    
    public List<Cart> getBySessionId(String sessionId){
        return cartDao.getBySessionId(sessionId);
    }
    
    public boolean existsBySessionId(String sessionId, String status){
        return cartDao.existsBySessionId(sessionId, status);
    }

}
