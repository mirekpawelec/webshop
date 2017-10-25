/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.webflow.execution.RequestContext;
import pl.pawelec.webshop.model.Address;
import pl.pawelec.webshop.model.Cart;
import pl.pawelec.webshop.model.Customer;
import pl.pawelec.webshop.model.Order;
import pl.pawelec.webshop.model.ShippingAddress;
import pl.pawelec.webshop.model.ShippingDetails;
import pl.pawelec.webshop.model.dao.AddressDao;
import pl.pawelec.webshop.model.dao.CartDao;
import pl.pawelec.webshop.model.dao.CartItemDao;
import pl.pawelec.webshop.model.dao.CustomerDao;
import pl.pawelec.webshop.model.dao.OrderDao;
import pl.pawelec.webshop.model.enum_.CartStatus;
import pl.pawelec.webshop.service.OrderService;
import pl.pawelec.webshop.model.dao.ShippingAddressDao;
import pl.pawelec.webshop.model.dao.ShippingDetailsDao;
import pl.pawelec.webshop.service.SystemClassService;

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
    private ShippingAddressDao shippingAddressDao;
    
    @Autowired
    private ShippingDetailsDao shippingDetailsDao;
    
    @Autowired
    private SystemClassService systemClassService;
    
    private final static String DELIVERY_METHOD_SYMBOL_CLASS = "delivery_method";
    private final static String PAYMENT_METHOD_SYMBOL_CLASS = "payment_method";
    
    
    
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
    
    public void fillInCustomerAndShippingAddressInOrder(Order order, Customer customer){
        order.setCustomer(customer);
        if(!Optional.ofNullable(order.getShippingAddress().getName()).isPresent()){
            order.getShippingAddress().setName(customer.getFirstName()+" "+customer.getLastName());
            order.getShippingAddress().setPhoneNumber(customer.getPhoneNumber()); 
            order.getShippingAddress().getAddress().setDoorNo(customer.getAddress().getDoorNo());
            order.getShippingAddress().getAddress().setStreetName(customer.getAddress().getStreetName());
            order.getShippingAddress().getAddress().setZipCode(customer.getAddress().getZipCode());
            order.getShippingAddress().getAddress().setAreaName(customer.getAddress().getAreaName());
            order.getShippingAddress().getAddress().setState(customer.getAddress().getState());
            order.getShippingAddress().getAddress().setCountry(customer.getAddress().getCountry());
        }
    }
    
    @Override
    public void fillInShippingDetailsInOrder(Order order, ShippingDetails shippingDetails) {
        order.getShippingDetails().setDeliveryMethod(shippingDetails.getDeliveryMethod());
        order.getShippingDetails().setDeliveryCost( 
                new BigDecimal(systemClassService.getByUniqueKey(DELIVERY_METHOD_SYMBOL_CLASS, shippingDetails.getDeliveryMethod()).getValue())
        );
        order.getShippingDetails().setPaymentMethod(shippingDetails.getPaymentMethod());
        order.getShippingDetails().setPaymentCost(
                new BigDecimal(systemClassService.getByUniqueKey(PAYMENT_METHOD_SYMBOL_CLASS, shippingDetails.getPaymentMethod()).getValue())
        );
        order.getShippingDetails().updateTotalCost();
    }
    
    public void fillInShippingAddressInOrder(Order order, ShippingAddress shippingAddress){
        order.getShippingAddress().setName(shippingAddress.getName());
        order.getShippingAddress().setPhoneNumber(shippingAddress.getPhoneNumber()); 
        order.getShippingAddress().getAddress().setDoorNo(shippingAddress.getAddress().getDoorNo());
        order.getShippingAddress().getAddress().setStreetName(shippingAddress.getAddress().getStreetName());
        order.getShippingAddress().getAddress().setZipCode(shippingAddress.getAddress().getZipCode());
        order.getShippingAddress().getAddress().setAreaName(shippingAddress.getAddress().getAreaName());
        order.getShippingAddress().getAddress().setState(shippingAddress.getAddress().getState());
        order.getShippingAddress().getAddress().setCountry(shippingAddress.getAddress().getCountry());
    }

    @Override
    public Order saveCustomerOrder(Order order) {
        System.out.println("order=" + order 
                       +"\n order.cart=" + order.getCart()
                       +"\n order.customer=" + order.getCustomer()
                       +"\n order.shippmentDetail=" + order.getShippingAddress()
                       +"\n order.shippmentDetail=" + order.getShippingDetails());
        
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
        
        ShippingAddress shippingAddress = order.getShippingAddress();
        if(order.getCustomer().getAddress().equals(order.getShippingAddress().getAddress())){
            shippingAddress.setAddress(address);
        } else {
            address = addressDao.createAndReturn(order.getShippingAddress().getAddress());
            shippingAddress.setAddress(address);
        }
        shippingAddress = shippingAddressDao.createAndReturn(shippingAddress);
        
        ShippingDetails shippingDetails = order.getShippingDetails();
        shippingDetails = shippingDetailsDao.createAndReturn(shippingDetails);
        
        Order orderToSave = createAndReturn(new Order(order.getCart(), customer, shippingAddress, shippingDetails));
        return orderToSave;
    }
    
    public void setFlowModelAttribute(RequestContext context){
        HttpServletRequest req = (HttpServletRequest)context.getExternalContext().getNativeRequest(); 
        String url = context.getFlowExecutionUrl();
        url = url.substring(url.indexOf("/", 1), url.length()) + "&";
        System.out.println(url);
        req.getSession().setAttribute("lastRequestUrl", url);
    }
}
