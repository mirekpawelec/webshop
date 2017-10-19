/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service;

import java.util.List;
import pl.pawelec.webshop.model.Customer;
import pl.pawelec.webshop.model.Order;
import pl.pawelec.webshop.model.ShippingDetail;

/**
 *
 * @author mirek
 */
public interface OrderService {
    void create(Order order);
    void update(Order order);
    void delete(Order order);
    void deleteById(Long id);
    void deleteAll();
    Order getOneById(Long id);
    List<Order> getAll();
    Long count();
    boolean exists(Long id);
    Order createAndReturn(Order order);
    void fillCustomerAndShippingAddress(Order order, Customer customer);
    void fillShippingAddressNewAddress(Order order, ShippingDetail shippingDetail);
    Order saveCustomerOrder(Order order);
}
