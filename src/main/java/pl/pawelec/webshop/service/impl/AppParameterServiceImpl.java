/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pawelec.webshop.model.AppParameter;
import pl.pawelec.webshop.model.dao.AppParameterDao;
import pl.pawelec.webshop.service.AppParameterService;

/**
 *
 * @author mirek
 */
@Service
@Transactional
public class AppParameterServiceImpl implements AppParameterService{
    @Autowired
    private AppParameterDao appParameterDao;
    
    @Override
    public void create(AppParameter appParameter) {
        appParameterDao.create(appParameter); 
    }

    @Override
    public void update(AppParameter appParameter) {
        appParameterDao.update(appParameter);
    }

    @Override
    public void delete(AppParameter appParameter) {
        appParameterDao.delete(appParameter);
    }

    @Override
    public void deleteById(Long id) {
        appParameterDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        appParameterDao.deleteAll();
    }

    @Override
    public AppParameter getOneById(Long id) {
        return appParameterDao.getOneById(id);
    }

    @Override
    public List<AppParameter> getAll() {
        return appParameterDao.getAll();
    }

    @Override
    public Long count() {
        return appParameterDao.count();
    }

    @Override
    public boolean exists(Long id) {
        return appParameterDao.exists(id);
    }
    
    public AppParameter getByUniqueKey(String symbol, String name) {
        return appParameterDao.getByUniqueKey(symbol, name);
    }
    
    public List<AppParameter> getBySymbol(String symbol){
        return getAll().stream().filter(sc->sc.getSymbol().equals(symbol)).collect(Collectors.toList());
    }
}
