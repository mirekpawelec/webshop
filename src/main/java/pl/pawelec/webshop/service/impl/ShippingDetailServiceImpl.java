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
import pl.pawelec.webshop.model.ShippingDetail;
import pl.pawelec.webshop.model.dao.ShippingDetailDao;
import pl.pawelec.webshop.service.ShippingDetailService;

/**
 *
 * @author mirek
 */
@Service
@Transactional
public class ShippingDetailServiceImpl implements ShippingDetailService{

    @Autowired
    private ShippingDetailDao shippingDetailDao;
    
    @Override
    public void create(ShippingDetail shippingDetail) {
        shippingDetailDao.create(shippingDetail);
    }

    @Override
    public void update(ShippingDetail shippingDetail) {
        shippingDetailDao.update(shippingDetail);
    }

    @Override
    public void delete(ShippingDetail shippingDetail) {
        shippingDetailDao.delete(shippingDetail);
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
    public ShippingDetail getOneById(Long id) {
        return shippingDetailDao.getOneById(id);
    }

    @Override
    public List<ShippingDetail> getAll() {
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
    public ShippingDetail createAndReturn(ShippingDetail shippingDetail) {
        return shippingDetailDao.createAndReturn(shippingDetail);
    }
    
}
