/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service;

import java.util.List;
import pl.pawelec.webshop.model.AppParameter;

/**
 *
 * @author mirek
 */
public interface AppParameterService {
    void create(AppParameter appParameter);
    void update(AppParameter appParameter);
    void delete(AppParameter appParameter);
    void deleteById(Long id);
    void deleteAll();
    AppParameter getOneById(Long id);
    List<AppParameter> getAll();
    Long count();
    boolean exists(Long id);
    AppParameter getByUniqueKey(String symbol, String name);
    List<AppParameter> getBySymbol(String symbol);
}
