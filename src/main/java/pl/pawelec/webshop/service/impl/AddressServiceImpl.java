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
import pl.pawelec.webshop.model.Address;
import pl.pawelec.webshop.model.dao.AddressDao;
import pl.pawelec.webshop.service.AddressService;

/**
 *
 * @author mirek
 */
@Service
@Transactional
public class AddressServiceImpl implements AddressService{
    @Autowired
    private AddressDao addressDao;
    
    @Override
    public void create(Address address) {
        addressDao.create(address);
    }

    @Override
    public void update(Address address) {
        addressDao.update(address);
    }

    @Override
    public void delete(Address address) {
        addressDao.delete(address);
    }

    @Override
    public void deleteById(Long id) {
        addressDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        addressDao.deleteAll();
    }

    @Override
    public Address getOneById(Long id) {
        return addressDao.getOneById(id);
    }

    @Override
    public List<Address> getAll() {
        return addressDao.getAll();
    }

    @Override
    public Long count() {
        return addressDao.count();
    }

    @Override
    public boolean exists(Long id) {
        return addressDao.exists(id);
    }  

    @Override
    public Address createAndReturn(Address address) {
        return addressDao.createAndReturn(address);
    }
}
