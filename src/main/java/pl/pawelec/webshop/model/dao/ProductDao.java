/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao;

import java.util.List;
import java.util.Optional;
import pl.pawelec.webshop.exception.NoProductFoundUnderProductNoException;
import pl.pawelec.webshop.model.Product;

/**
 *
 * @author mirek
 */
public interface ProductDao extends Dao<Product>{
    List<Product> getByUnitsPrice(Double minPrice, Double maxPrice);
    List<Product> getByManufacturer(String manufacturer);
    List<Product> getByStatus(String status);
    Product getOneByProductNo(String productNo);
    List<String> getAllManufacturers();
    List<String> getAllCategories();
}
