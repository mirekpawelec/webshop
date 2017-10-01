/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pawelec.webshop.model.Storagearea;
import pl.pawelec.webshop.model.dao.StorageareaDao;
import pl.pawelec.webshop.service.StorageareaService;

/**
 *
 * @author mirek
 */
@Service
@Transactional
public class StorageareaServiceImpl implements StorageareaService{

    @Autowired
    private StorageareaDao storageareaDao;
    
    @Override
    public void create(Storagearea storagearea) {
        storageareaDao.create(storagearea);
    }

    @Override
    public void update(Storagearea storagearea) {
        storageareaDao.update(storagearea);
    }

    @Override
    public void delete(Storagearea storagearea) {
        storageareaDao.delete(storagearea);
    }

    @Override
    public void deleteById(Long id){
        storageareaDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        storageareaDao.deleteAll();
    }

    @Override
    public Storagearea getById(Long id) {
        return storageareaDao.getOneById(id);
    }

    @Override
    public List<Storagearea> getAll() {
        return storageareaDao.getAll();
    }

    @Override
    public Long count() {
        return storageareaDao.count();
    }

    @Override
    public boolean exists(Long id) {
        return storageareaDao.exists(id);
    }

    @Override
    public List<Storagearea> getByDescription(String wholeDescriptionOrPart) {
        return storageareaDao.getByDescription(wholeDescriptionOrPart);
    }
}
