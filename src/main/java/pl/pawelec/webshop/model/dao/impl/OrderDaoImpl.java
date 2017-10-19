/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao.impl;

import org.springframework.stereotype.Repository;
import pl.pawelec.webshop.model.Order;
import pl.pawelec.webshop.model.dao.AbstrDao;
import pl.pawelec.webshop.model.dao.OrderDao;

/**
 *
 * @author mirek
 */
@Repository
public class OrderDaoImpl extends AbstrDao<Order> implements OrderDao{
    public Order createAndReturn(Order order) {
        getEntityManager().persist(order);
        return order;
    }
}
