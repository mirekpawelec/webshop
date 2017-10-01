package pl.pawelec.webshop.service.impl;


import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pawelec.webshop.exception.NoProductIdFoundException;
import pl.pawelec.webshop.model.Product;
import pl.pawelec.webshop.model.dao.ProductDao;
import pl.pawelec.webshop.service.ProductService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mirek
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService, Serializable{
    @Autowired
    private ProductDao productDao;

    @Override
    public void create(Product product) {
        productDao.create(product);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void delete(Product product) {
        productDao.delete(product);
    }

    @Override
    public void deleteById(Long id) {
        productDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        productDao.deleteAll();
    }

    @Override
    public Product getOneById(Long id) {
        return productDao.getOneById(id);
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public Long count() {
        return productDao.count();
    }

    @Override
    public boolean exists(Long id) {
        return productDao.exists(id);
    }

    @Override
    public List<Product> getByUnitsPrice(Double minPrice, Double maxPrice) {
        return productDao.getByUnitsPrice(minPrice, maxPrice);
    }

    @Override
    public Product getOneByProductNo(String productNo){
        return productDao.getOneByProductNo(productNo);
    }

    @Override
    public List<String> getAllCategories() {
        return productDao.getAllCategories();
    }

    @Override
    public List<String> getAllManufacturers() {
        return productDao.getAllManufacturers();
    }

    @Override
    public List<Product> getByManufacturer(String manufacturer) {
        return productDao.getByManufacturer(manufacturer);
    }

    @Override
    public List<Product> getByStatus(String status) {
        return productDao.getByStatus(status);
    }
}
