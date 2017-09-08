/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.pawelec.webshop.model.Product;
import pl.pawelec.webshop.model.ProductStatus;
import pl.pawelec.webshop.service.ProductService;

/**
 *
 * @author mirek
 */
@SessionAttributes("productStatus")
@RequestMapping(value = "/products")
@Controller
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @RequestMapping
    public String allProducts(Model model){
        System.out.println("### show all products controller");
        List<Product> products = productService.getAll();
        products.stream().forEach(p -> p.setStatus(ProductStatus.valueOf(p.getStatus()).getProductStatusDescription()) );
        model.addAttribute("products", products);
        return "products";
    }
    
    @RequestMapping("/product")
    public String getProductById(@RequestParam("id") String productId, Model model){
        System.out.println("### show product controller");
        System.out.println("@@@ id received=" + productId);
        Product product = productService.getOneById(Long.valueOf(productId));
        product.setStatus( ProductStatus.valueOf(product.getStatus()).getProductStatusDescription() );
        model.addAttribute("product", product);
        return "product";
    }
    
    @RequestMapping("/modify")
    public String modyfyProductByIdForm(@RequestParam("id") String productId, Model model, HttpServletRequest request){
        System.out.println("### modify product controller");
        System.out.println("@@@ id received=" + productId);
        model.addAttribute("newProduct", productService.getOneById(Long.valueOf(productId)));
        return "addProduct";
    }
    
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String processModyfyProductByIdForm(@RequestParam("id") String productId, @ModelAttribute("newProduct") @Valid Product productToBeModify, BindingResult result){
        System.out.println("### process modify product controller");
        if(result.hasErrors()) return "addProduct";
        System.out.println("@@@ id received=" + productId);
        productToBeModify.setProductId(Long.valueOf(productId));
        productService.update(productToBeModify);
        return "redirect:/products";
    }
    
    @RequestMapping("/delete")
    public String deleteProductById(@RequestParam("id") String productId, Model model){
        System.out.println("### delete product controller");
        System.out.println("@@@ id received=" + productId);
        productService.deleteById(Long.valueOf(productId));
        return "redirect:/products";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addProductForm(Model model, HttpServletRequest request){
        System.out.println("### add new product controller (GET)");
        Product product = new Product();
        model.addAttribute("newProduct", product);
        return "addProduct";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddProductForm(@ModelAttribute("newProduct") @Valid Product productToBeAdd, BindingResult result){
        System.out.println("### process add new product controller (POST)");
        if(result.hasErrors()) return "addProduct";
        
        String[] suppresedFields = result.getSuppressedFields();
        if(suppresedFields.length > 0) throw new RuntimeException("Próba wiązania niedozwolonych pól: " + StringUtils.arrayToCommaDelimitedString(suppresedFields));
        
        System.out.println(new Product.Builder().withProductNo(productToBeAdd.getProductNo()).withName(productToBeAdd.getName()).withManufacturer(productToBeAdd.getManufacturer())
                .withCategory(productToBeAdd.getCategory()).withDescription(productToBeAdd.getDescription()).withUnitPrice(productToBeAdd.getUnitPrice())
                .withQuantityInBox(productToBeAdd.getQuantityInBox()).build());
        
        productService.create(productToBeAdd);
        return "redirect:/products";
    }

    @InitBinder(value = "newProduct")
    public void initializeBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("productId", "createDate");
    }
    
    @ModelAttribute("productStatus")
    public List<ProductStatus> getAllProductStatus(){
        return Arrays.asList(ProductStatus.values());
    }
    
}
