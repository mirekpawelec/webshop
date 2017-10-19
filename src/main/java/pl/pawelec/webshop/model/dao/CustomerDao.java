/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao;

import pl.pawelec.webshop.model.Customer;

/**
 *
 * @author mirek
 */
public interface CustomerDao extends Dao<Customer>{
    Customer createAndReturn(Customer customer);
}