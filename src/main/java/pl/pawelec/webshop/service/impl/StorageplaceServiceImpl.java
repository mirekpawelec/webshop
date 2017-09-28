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
import pl.pawelec.webshop.model.Storageplace;
import pl.pawelec.webshop.model.dao.StorageplaceDao;
import pl.pawelec.webshop.service.StorageplaceService;

/**
 *
 * @author mirek
 */
@Service
@Transactional 
public class StorageplaceServiceImpl implements StorageplaceService{
    @Autowired
    private StorageplaceDao storageplaceDao;
    
    @Override
    public void create(Storageplace storageplace) {
        storageplaceDao.create(storageplace);
    }

    @Override
    public void update(Storageplace storageplace) {
        storageplaceDao.update(storageplace);
    }

    @Override
    public void delete(Storageplace storageplace) {
        storageplaceDao.delete(storageplace);
    }

    @Override
    public void deleteById(Long Id) {
        storageplaceDao.deleteById(Id);
    }

    @Override
    public void deleteAll() {
        storageplaceDao.deleteAll();
    }

    @Override
    public Storageplace getById(Long Id) {
        return storageplaceDao.getOneById(Id);
    }

    @Override
    public List<Storageplace> getAll() {
        return storageplaceDao.getAll();
    }

    @Override
    public Long count() {
        return storageplaceDao.count();
    }

    @Override
    public boolean exists(Long Id) {
        return storageplaceDao.exists(Id);
    }

    @Override
    public Storageplace getByPlaceNo(String placeNo) {
        return storageplaceDao.getByPlaceNo(placeNo);
    }
    
    public List<Storageplace> getByType(String type){
        return storageplaceDao.getByType(type);
    }
}
