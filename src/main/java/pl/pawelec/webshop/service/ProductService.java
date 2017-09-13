/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service;

import java.util.List;
import java.util.Optional;
import pl.pawelec.webshop.model.Product;

/**
 *
 * @author mirek
 */
public interface ProductService {
    void create(Product product);
    void update(Product product);
    void delete(Product product);
    void deleteById(Long Id);
    void deleteAll();
    Product getOneById(Long Id);
    List<Product> getAll();
    Long count();
    boolean exists(Long Id);
    List<Product> getByUnitsPrice(Double minPrice, Double maxPrice);
    Product getOneByProductNo(String productNo);
}
