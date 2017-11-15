/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service;

import java.util.List;
import pl.pawelec.webshop.model.Rule;

/**
 *
 * @author mirek
 */
public interface RuleService {
    void create(Rule rule);
    void update(Rule rule);
    void delete(Rule rule);
    void deleteById(Long id);
    void deleteAll();
    Rule getOneById(Long id);
    List<Rule> getAll();
    Long count();
    boolean exists(Long id);
    Long createAndGetId(Rule rule);
}
