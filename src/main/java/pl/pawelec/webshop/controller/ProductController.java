/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.pawelec.webshop.model.Product;
import pl.pawelec.webshop.service.ProductService;

/**
 *
 * @author mirek
 */
@RequestMapping(value = "/products")
@Controller
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @RequestMapping
    public String allProducts(Model model){
        System.out.println("### show all products controller");
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "products";
    }
    
    @RequestMapping("/product")
    public String getProductById(@RequestParam("id") String productId, Model model){
        System.out.println("### show product controller");
        System.out.println("@@@ id received=" + productId);
        model.addAttribute("product", productService.getOneById( Long.valueOf(productId) ));
        return "product";
    }
    
    @RequestMapping("/delete")
    public String deleteProductById(@RequestParam("id") String productId, Model model){
        System.out.println("### delete product controller");
        System.out.println("@@@ id received=" + productId);
        productService.deleteById( Long.valueOf(productId) );
        return "redirect:/products";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addProductForm(Model model){
        System.out.println("### add new product controller (GET)");
        Product product = new Product();
        model.addAttribute("newProduct", product);
        return "addProduct";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddProductForm(@ModelAttribute("newProduct") @Valid Product productToBeAdd, BindingResult result){
        System.out.println("### process add new product controller (POST)");
        
        System.out.println(result.hasErrors());
        if(result.hasErrors()) return "addProduct";
        
        String[] suppresedFields = result.getSuppressedFields();
        if(suppresedFields.length > 0) throw new RuntimeException("Próba wiązania niedozwolonych pól: " + StringUtils.arrayToCommaDelimitedString(suppresedFields));
        
        System.out.println(new Product.Builder().withProductNo(productToBeAdd.getProductNo()).withName(productToBeAdd.getName()).withManufacturer(productToBeAdd.getManufacturer())
                .withCategory(productToBeAdd.getCategory()).withDescription(productToBeAdd.getDescription()).withUnitPrice(productToBeAdd.getUnitPrice())
                .withQuantityInBox(productToBeAdd.getQuantityInBox()).build());
        
        productService.create(productToBeAdd);
        
        return "redirect:/products";
    }

    @InitBinder
    public void initializeBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("productId", "status", "createDate");
    }
}
