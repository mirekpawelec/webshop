/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service;

import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import pl.pawelec.webshop.model.Product;
import pl.pawelec.webshop.model.Storagearea;

/**
 *
 * @author mirek
 */
public interface StorageareaService {
    void create(Storagearea storagearea);
    void update(Storagearea storagearea);
    void delete(Storagearea storagearea);
    void deleteById(Long Id);
    void deleteAll();
    Storagearea getById(Long Id);
    List<Storagearea> getAll();
    Long count();
    boolean exists(Long Id);
    List<Storagearea> getByDescription(String wholeDescriptionOrPart);
}
