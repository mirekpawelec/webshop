/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao;

import pl.pawelec.webshop.model.SystemClass;

/**
 *
 * @author mirek
 */
public interface SystemClassDao extends Dao<SystemClass>{
    SystemClass getByUniqueKey(String symbol, String name);
}
