/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao;

import java.util.List;
import pl.pawelec.webshop.model.Order;

/**
 *
 * @author mirek
 */
public interface OrderDao extends Dao<Order>{
    Order createAndReturn(Order order);
    List<Order> getByUserLogin(String login);
}
