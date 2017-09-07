/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao;

import java.util.List;
import pl.pawelec.webshop.model.Product;

/**
 *
 * @author mirek
 */
public interface ProductDao extends Dao<Product>{
    // specjal method's class ProductDaoImp
    
    List<Product> getByUnitsPrice(Double minPrice, Double maxPrice);
}
