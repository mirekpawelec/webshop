/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service;

import java.util.List;
import pl.pawelec.webshop.model.Customer;

/**
 *
 * @author mirek
 */
public interface CustomerService {
    void create(Customer customer);
    void update(Customer customer);
    void delete(Customer customer);
    void deleteById(Long id);
    void deleteAll();
    Customer getOneById(Long id);
    List<Customer> getAll();
    Long count();
    boolean exists(Long id);
    Customer createAndReturn(Customer customer);
}
