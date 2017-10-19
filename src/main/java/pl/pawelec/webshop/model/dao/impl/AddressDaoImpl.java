/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao.impl;

import org.springframework.stereotype.Repository;
import pl.pawelec.webshop.model.Address;
import pl.pawelec.webshop.model.dao.AbstrDao;
import pl.pawelec.webshop.model.dao.AddressDao;

/**
 *
 * @author mirek
 */
@Repository
public class AddressDaoImpl extends AbstrDao<Address> implements AddressDao{
    public Address createAndReturn(Address address){
        getEntityManager().persist(address);
        return address;
    };
}
