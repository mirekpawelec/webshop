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
import pl.pawelec.webshop.model.ShippingAddress;
import pl.pawelec.webshop.model.dao.ShippingAddressDao;
import pl.pawelec.webshop.service.ShippingAddressService;

/**
 *
 * @author mirek
 */
@Service
@Transactional
public class ShippingAddressServiceImpl implements ShippingAddressService{

    @Autowired
    private ShippingAddressDao shippingDetailDao;
    
    @Override
    public void create(ShippingAddress shippingAddress) {
        shippingDetailDao.create(shippingAddress);
    }

    @Override
    public void update(ShippingAddress shippingAddress) {
        shippingDetailDao.update(shippingAddress);
    }

    @Override
    public void delete(ShippingAddress shippingAddress) {
        shippingDetailDao.delete(shippingAddress);
    }

    @Override
    public void deleteById(Long id) {
        shippingDetailDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        shippingDetailDao.deleteAll();
    }

    @Override
    public ShippingAddress getOneById(Long id) {
        return shippingDetailDao.getOneById(id);
    }

    @Override
    public List<ShippingAddress> getAll() {
        return shippingDetailDao.getAll();
    }

    @Override
    public Long count() {
        return shippingDetailDao.count();
    }

    @Override
    public boolean exists(Long id) {
        return shippingDetailDao.exists(id);
    }

    @Override
    public ShippingAddress createAndReturn(ShippingAddress shippingAddress) {
        return shippingDetailDao.createAndReturn(shippingAddress);
    }
    
}
