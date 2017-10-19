/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pawelec.webshop.model.Address;
import pl.pawelec.webshop.model.Cart;
import pl.pawelec.webshop.model.CartItem;
import pl.pawelec.webshop.model.Customer;
import pl.pawelec.webshop.model.Order;
import pl.pawelec.webshop.model.ShippingDetail;
import pl.pawelec.webshop.model.dao.AddressDao;
import pl.pawelec.webshop.model.dao.CartDao;
import pl.pawelec.webshop.model.dao.CartItemDao;
import pl.pawelec.webshop.model.dao.CustomerDao;
import pl.pawelec.webshop.model.dao.OrderDao;
import pl.pawelec.webshop.model.dao.ShippingDetailDao;
import pl.pawelec.webshop.model.enum_.CartStatus;
import pl.pawelec.webshop.service.OrderService;

/**
 *
 * @author mirek
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderDao orderDao;
    
    @Autowired
    private CartDao cartDao;
    
    @Autowired
    private CartItemDao cartItemDao;
    
    @Autowired
    private AddressDao addressDao;
    
    @Autowired
    private CustomerDao customerDao;
    
    @Autowired
    private ShippingDetailDao shippingDetailDao;
    
    public void create(Order order) {      
        orderDao.create(order);
    }

    public void update(Order order) {
        orderDao.update(order);
    }

    public void delete(Order order) {
        orderDao.delete(order);
    }

    public void deleteById(Long id) {
        orderDao.deleteById(id);
    }

    public void deleteAll() {
        orderDao.deleteAll();
    }

    public Order getOneById(Long id) {
        return orderDao.getOneById(id);
    }

    public List<Order> getAll() {
        return orderDao.getAll();
    }

    public Long count() {
        return orderDao.count();
    }

    public boolean exists(Long id) {
        return orderDao.exists(id);
    }
    
    public Order createAndReturn(Order order) {
        return orderDao.createAndReturn(order);
    }
    
    public void fillCustomerAndShippingAddress(Order order, Customer customer){
        order.setCustomer(customer);
        order.getShippingDetail().setName(customer.getFirstName()+" "+customer.getLastName());
        order.getShippingDetail().getShippingAddress().setDoorNo(customer.getAddress().getDoorNo());
        order.getShippingDetail().getShippingAddress().setStreetName(customer.getAddress().getStreetName());
        order.getShippingDetail().getShippingAddress().setZipCode(customer.getAddress().getZipCode());
        order.getShippingDetail().getShippingAddress().setAreaName(customer.getAddress().getAreaName());
        order.getShippingDetail().getShippingAddress().setState(customer.getAddress().getState());
        order.getShippingDetail().getShippingAddress().setCountry(customer.getAddress().getCountry());
    }
    
    public void fillShippingAddressNewAddress(Order order, ShippingDetail shippingDetail){
        order.getShippingDetail().setName(shippingDetail.getName());
        order.getShippingDetail().getShippingAddress().setDoorNo(shippingDetail.getShippingAddress().getDoorNo());
        order.getShippingDetail().getShippingAddress().setStreetName(shippingDetail.getShippingAddress().getStreetName());
        order.getShippingDetail().getShippingAddress().setZipCode(shippingDetail.getShippingAddress().getZipCode());
        order.getShippingDetail().getShippingAddress().setAreaName(shippingDetail.getShippingAddress().getAreaName());
        order.getShippingDetail().getShippingAddress().setState(shippingDetail.getShippingAddress().getState());
        order.getShippingDetail().getShippingAddress().setCountry(shippingDetail.getShippingAddress().getCountry());
    }

    @Override
    public Order saveCustomerOrder(Order order) {
        System.out.println("order=" + order 
                       +"\n order.cart=" + order.getCart()
                       +"\n order.customer=" + order.getCustomer()
                       +"\n order.shippmentDetail=" + order.getShippingDetail());
        
        Cart cart = cartDao.getOneById(order.getCart().getCartId());
        cart.setLastModificationDate(LocalDateTime.now());
        cart.setStatus(CartStatus.FI.name());
        cartDao.update(cart);
        
        cartItemDao.getByCartId(order.getCart().getCartId()).stream().forEach(ci -> {
            ci.setLastModificationDate(LocalDateTime.now());
            ci.setStatus(CartStatus.FI.name());
            cartItemDao.update(ci); 
        });
        
        Address address = addressDao.createAndReturn(order.getCustomer().getAddress());
        Customer customer = order.getCustomer();
        customer.setAddress(address);
        customer = customerDao.createAndReturn(customer);
        
        
        ShippingDetail shippingDetail = order.getShippingDetail();
        if(order.getCustomer().getAddress().equals(order.getShippingDetail().getShippingAddress())){
            shippingDetail.setShippingAddress(address);
        } else {
            address = addressDao.createAndReturn(order.getShippingDetail().getShippingAddress());
            shippingDetail.setShippingAddress(address);
        }
        shippingDetail = shippingDetailDao.createAndReturn(shippingDetail);
        
        Order orderToSave = createAndReturn(new Order(order.getCart(), customer, shippingDetail));
        return orderToSave;
    }
}
