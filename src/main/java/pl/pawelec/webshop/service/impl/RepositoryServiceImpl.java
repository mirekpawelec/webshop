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
import pl.pawelec.webshop.model.Repository;
import pl.pawelec.webshop.model.dao.RepositoryDao;
import pl.pawelec.webshop.service.RepositoryService;

/**
 *
 * @author mirek
 */
@Service
@Transactional
public class RepositoryServiceImpl implements RepositoryService{

    @Autowired
    private RepositoryDao repositoryDao;
    
    @Override
    public void create(Repository repository) {
        repositoryDao.create(repository);
    }

    @Override
    public void update(Repository repository) {
        repositoryDao.update(repository);
    }

    @Override
    public void delete(Repository repository) {
        repositoryDao.delete(repository);
    }

    @Override
    public void deleteById(Long Id) {
        repositoryDao.deleteById(Id);
    }

    @Override
    public void deleteAll() {
        repositoryDao.deleteAll();
    }

    @Override
    public Repository getById(Long Id) {
        return repositoryDao.getOneById(Id);
    }

    @Override
    public List<Repository> getAll() {
        return repositoryDao.getAll();
    }

    @Override
    public Long count() {
        return repositoryDao.count();
    }

    @Override
    public boolean exists(Long Id) {
        return repositoryDao.exists(Id);
    }

    @Override
    public Repository getByLoadunitNo(String loadunitNo) {
        return repositoryDao.getByLoadunitNo(loadunitNo);
    }
    
}
