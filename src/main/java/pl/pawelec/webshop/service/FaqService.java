/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service;

import java.util.List;
import pl.pawelec.webshop.model.Faq;

/**
 *
 * @author mirek
 */
public interface FaqService {
    void create(Faq faq);
    void update(Faq faq);
    void delete(Faq faq);
    void deleteById(Long id);
    void deleteAll();
    Faq getOneById(Long id);
    List<Faq> getAll();
    Long count();
    boolean exists(Long id);
    Long createAndGetId(Faq faq);
}
