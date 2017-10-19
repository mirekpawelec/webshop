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
import pl.pawelec.webshop.model.SystemClass;
import pl.pawelec.webshop.model.dao.SystemClassDao;
import pl.pawelec.webshop.service.SystemClassService;

/**
 *
 * @author mirek
 */
@Service
@Transactional
public class SystemClassServiceImpl implements SystemClassService{
    @Autowired
    private SystemClassDao systemClassDao;
    
    @Override
    public void create(SystemClass systemClass) {
        systemClassDao.create(systemClass); 
    }

    @Override
    public void update(SystemClass systemClass) {
        systemClassDao.update(systemClass);
    }

    @Override
    public void delete(SystemClass systemClass) {
        systemClassDao.delete(systemClass);
    }

    @Override
    public void deleteById(Long id) {
        systemClassDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        systemClassDao.deleteAll();
    }

    @Override
    public SystemClass getOneById(Long id) {
        return systemClassDao.getOneById(id);
    }

    @Override
    public List<SystemClass> getAll() {
        return systemClassDao.getAll();
    }

    @Override
    public Long count() {
        return systemClassDao.count();
    }

    @Override
    public boolean exists(Long id) {
        return systemClassDao.exists(id);
    }
    
    public SystemClass getByUniqueKey(String symbol, String name) {
        return systemClassDao.getByUniqueKey(symbol, name);
    }
}
