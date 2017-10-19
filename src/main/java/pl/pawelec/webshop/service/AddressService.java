/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service;

import java.util.List;
import pl.pawelec.webshop.model.Address;

/**
 *
 * @author mirek
 */
public interface AddressService {
    void create(Address address);
    void update(Address address);
    void delete(Address address);
    void deleteById(Long id);
    void deleteAll();
    Address getOneById(Long id);
    List<Address> getAll();
    Long count();
    boolean exists(Long id);
    Address createAndReturn(Address address);
}
