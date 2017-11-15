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
import pl.pawelec.webshop.model.Faq;
import pl.pawelec.webshop.model.dao.FaqDao;
import pl.pawelec.webshop.service.FaqService;

/**
 *
 * @author mirek
 */
@Service
@Transactional
public class FaqServiceImpl implements FaqService{
    @Autowired
    private FaqDao faqDao;
    

    
    public void create(Faq faq) {
        faqDao.create(faq);
    }

    public void update(Faq faq) {
        faqDao.update(faq);
    }

    public void delete(Faq faq) {
        faqDao.delete(faq);
    }

    public void deleteById(Long id) {
        faqDao.deleteById(id);
    }

    public void deleteAll() {
        faqDao.deleteAll();
    }

    public Faq getOneById(Long id) {
        return faqDao.getOneById(id);
    }

    public List<Faq> getAll() {
        return faqDao.getAll();
    }

    public Long count() {
        return faqDao.count();
    }

    public boolean exists(Long id) {
        return faqDao.exists(id);
    }
    
    public Long createAndGetId(Faq faq){
        return faqDao.createAndGetId(faq);
    }
}
