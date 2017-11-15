/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao;

import pl.pawelec.webshop.model.Rule;

/**
 *
 * @author mirek
 */
public interface RuleDao extends Dao<Rule>{
    Long createAndGetId(Rule rule);
}