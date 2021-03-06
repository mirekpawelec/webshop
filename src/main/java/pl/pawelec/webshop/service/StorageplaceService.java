/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service;

import java.util.List;
import pl.pawelec.webshop.model.Storageplace;

/**
 *
 * @author mirek
 */       
public interface StorageplaceService {
    void create(Storageplace storageplace);
    void update(Storageplace storageplace);
    void delete(Storageplace storageplace);
    void deleteById(Long id);
    void deleteAll();
    Storageplace getById(Long id);
    List<Storageplace> getAll();
    Long count();
    boolean exists(Long id);
    Storageplace getByPlaceNo(String placeNo);
    List<Storageplace> getByType(String type);
}
