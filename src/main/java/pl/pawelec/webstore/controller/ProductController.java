/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webstore.controller;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.pawelec.webstore.model.Product;

/**
 *
 * @author mirek
 */
@RequestMapping(value = "/products")
@Controller
public class ProductController {
    
    @RequestMapping
    public String allProducts(Model model){
        System.out.println("### show all products controller");
        return "products";
    }
    
    @RequestMapping("/product")
    public String getOneProduct(Model model){
        System.out.println("### show product controller");
        return "product";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addProductForm(Model model){
        System.out.println("### add new product controller (GET)");
        model.addAttribute("newProduct", new Product());
        return "addProduct";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddProductForm(@ModelAttribute("newProduct") @Valid Product productToBeAdd, Model model, BindingResult result){
        System.out.println("### process add new product controller (POST)");
        
        System.out.println(result.hasErrors());
        if(result.hasErrors()) return "addProduct";
        
        String[] suppresedFields = result.getSuppressedFields();
        if(suppresedFields.length > 0) throw new RuntimeException("Próba wiązania niedozwolonych pól: " + StringUtils.arrayToCommaDelimitedString(suppresedFields));
        
        System.out.println(new Product.Builder()
                                    .withProductNo(productToBeAdd.getProductNo())
                                    .withName(productToBeAdd.getName())
                                    .withManufacturer(productToBeAdd.getManufacturer())
                                    .withCategory(productToBeAdd.getCategory())
                                    .withDescription(productToBeAdd.getDescription())
                                    .withUnitPrice(productToBeAdd.getUnitPrice())
                                    .withQuantityInBox(productToBeAdd.getQuantityInBox())
                                    .build());
        
        
        
        return "redirect:/products";
    }

    @InitBinder
    public void initializeBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("productId", "status", "createDate");
    }
}
