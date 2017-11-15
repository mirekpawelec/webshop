/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.pawelec.webshop.model.Customer;
import pl.pawelec.webshop.service.CustomerService;

/**
 *
 * @author mirek
 */
public class CustomerIdToCustomerConverter implements Converter<Object, Customer>{
    @Autowired
    private CustomerService customerService;
    
    @Override
    public Customer convert(Object element) {
        Long id = (Long) element;
        Customer customer = customerService.getOneById(id);
        return customer;
    }
    
}
