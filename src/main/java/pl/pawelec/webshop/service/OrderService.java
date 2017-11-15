/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service;

import java.util.List;
import org.springframework.webflow.execution.RequestContext;
import pl.pawelec.webshop.model.Customer;
import pl.pawelec.webshop.model.Order;
import pl.pawelec.webshop.model.ShippingAddress;
import pl.pawelec.webshop.model.ShippingDetails;

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
    void fillInCustomerAndShippingAddressInOrder(Order order, Customer customer);
    void fillInShippingDetailsInOrder(Order order, ShippingDetails shippingDetails);
    void fillInShippingAddressInOrder(Order order, ShippingAddress shippingAddress);
    Order saveCustomerOrder(Order order);
    void setFlowModelAttribute(RequestContext context);
    void checkUserAndFillInCustomer(Order order, Customer customer);
    void associateUserWithCustomer(Order order);
    List<Order> getByUserLogin(String login);
}
