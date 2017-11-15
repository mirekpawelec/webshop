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
import pl.pawelec.webshop.model.Rule;
import pl.pawelec.webshop.model.dao.RuleDao;
import pl.pawelec.webshop.service.RuleService;

/**
 *
 * @author mirek
 */
@Service
@Transactional
public class RuleServiceImpl implements RuleService{
    @Autowired
    private RuleDao ruleDao;
    
    
    
    public void create(Rule rule) {
        ruleDao.create(rule);
    }

    public void update(Rule rule) {
        ruleDao.update(rule);
    }

    public void delete(Rule rule) {
        ruleDao.delete(rule);
    }

    public void deleteById(Long id) {
        ruleDao.deleteById(id);
    }

    public void deleteAll() {
        ruleDao.deleteAll();
    }

    public Rule getOneById(Long id) {
        return ruleDao.getOneById(id);
    }

    public List<Rule> getAll() {
        return ruleDao.getAll();
    }

    public Long count() {
        return ruleDao.count();
    }

    public boolean exists(Long id) {
        return ruleDao.exists(id);
    }

    public Long createAndGetId(Rule rule) {
        return ruleDao.createAndGetId(rule);
    }
    
}
