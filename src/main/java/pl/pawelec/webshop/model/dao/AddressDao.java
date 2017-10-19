/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao;

import pl.pawelec.webshop.model.Address;

/**
 *
 * @author mirek
 */
public interface AddressDao extends Dao<Address>{
    Address createAndReturn(Address address);
}
