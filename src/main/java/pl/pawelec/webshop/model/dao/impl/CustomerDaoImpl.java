/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao.impl;

import org.springframework.stereotype.Repository;
import pl.pawelec.webshop.model.dao.AbstrDao;
import pl.pawelec.webshop.model.dao.CustomerDao;
import pl.pawelec.webshop.model.Customer;

/**
 *
 * @author mirek
 */
@Repository
public class CustomerDaoImpl extends AbstrDao<Customer> implements CustomerDao{
    public Customer createAndReturn(Customer customer) {
        getEntityManager().persist(customer);
        return customer;
    }
}