/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service;

import java.util.List;
import pl.pawelec.webshop.model.SystemClass;

/**
 *
 * @author mirek
 */
public interface SystemClassService {
    void create(SystemClass systemClass);
    void update(SystemClass systemClass);
    void delete(SystemClass systemClass);
    void deleteById(Long id);
    void deleteAll();
    SystemClass getOneById(Long id);
    List<SystemClass> getAll();
    Long count();
    boolean exists(Long id);
    SystemClass getByUniqueKey(String symbol, String name);
}
