/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao.impl;

import org.springframework.stereotype.Repository;
import pl.pawelec.webshop.model.Faq;
import pl.pawelec.webshop.model.dao.AbstrDao;
import pl.pawelec.webshop.model.dao.FaqDao;

/**
 *
 * @author mirek
 */
@Repository
public class FaqDaoImpl extends AbstrDao<Faq> implements FaqDao{
    
    public Long createAndGetId(Faq faq) {
        getEntityManager().persist(faq);
        return faq.getFaqId();
    }
    
}
