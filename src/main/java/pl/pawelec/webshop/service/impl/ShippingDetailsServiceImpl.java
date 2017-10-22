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
import pl.pawelec.webshop.model.ShippingDetails;
import pl.pawelec.webshop.model.dao.ShippingDetailsDao;
import pl.pawelec.webshop.service.ShippingDetailsService;

/**
 *
 * @author mirek
 */
@Service
@Transactional
public class ShippingDetailsServiceImpl implements ShippingDetailsService{
    
    @Autowired
    private ShippingDetailsDao shippingDetailsDao;
    
    
    
    public void create(ShippingDetails shippingDetails) {
        shippingDetailsDao.create(shippingDetails);
    }

    public void update(ShippingDetails shippingDetails) {
        shippingDetailsDao.update(shippingDetails);
    }

    public void delete(ShippingDetails shippingDetails) {
        shippingDetailsDao.delete(shippingDetails);
    }

    public void deleteById(Long id) {
        shippingDetailsDao.deleteById(id);
    }

    public void deleteAll() {
        shippingDetailsDao.deleteAll();
    }

    public ShippingDetails getOneById(Long id) {
        return shippingDetailsDao.getOneById(id);
    }

    public List<ShippingDetails> getAll() {
        return shippingDetailsDao.getAll();
    }

    public Long count() {
        return shippingDetailsDao.count();
    }

    public boolean exists(Long id) {
        return shippingDetailsDao.exists(id);
    }
    
    public ShippingDetails createAndReturn(ShippingDetails shippingDetails){
        return shippingDetailsDao.createAndReturn(shippingDetails);
    }
    
}
