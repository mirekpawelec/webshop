/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.pawelec.webshop.model.ProductFilter;
import pl.pawelec.webshop.model.Product;
import pl.pawelec.webshop.model.enum_.CartStatus;
import pl.pawelec.webshop.model.enum_.ProductStatus;
import pl.pawelec.webshop.service.CartService;
import pl.pawelec.webshop.service.ProductService;

/**
 *
 * @author mirek
 */
@SessionAttributes(names = {"sessionId"})
@RequestMapping("/home")
@Controller
public class HomeController {
    
    private Logger logger = Logger.getLogger(HomeController.class);
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CartService cartService;
    
    
    
    @RequestMapping
    public String getAllProducts(@ModelAttribute("filterOfProducts") ProductFilter filterOfProducts, Model model
                               , BindingResult result, HttpServletRequest request){
        logger.info("### getAllProducts");
        if(result.getSuppressedFields().length > 0){
            throw new RuntimeException("Próba wiązania niedozwolonych pól" + StringUtils.arrayToCommaDelimitedString(result.getSuppressedFields()));
        }
        List<Product> afterFilteringProducts = productService.getByStatus(ProductStatus.OK.name()).parallelStream()
                .filter( (product) -> { if( filterOfProducts.isInStock() )
                                            return product.getRepositorySet().size() > 0;
                                        else
                                            return true;
                    })
                .filter( (product) -> { if( filterOfProducts.getManufacturer()!=null && !filterOfProducts.getManufacturer().equals("NONE") ) 
                                            return product.getManufacturer().equals(filterOfProducts.getManufacturer()); 
                                        else 
                                            return true; 
                    })
                .filter( (product) -> { if( filterOfProducts.getCategory()!=null && !filterOfProducts.getCategory().equals("NONE") ) 
                                            return product.getCategory().equals(filterOfProducts.getCategory());
                                        else
                                            return true;
                    })
                .filter( (product) -> { if( filterOfProducts.getMinUnitPrice()!=null && !filterOfProducts.getMinUnitPrice().equals(BigDecimal.valueOf(0)) ) 
                                            return product.getUnitPrice().compareTo(filterOfProducts.getMinUnitPrice()) >= 0;
                                        else
                                            return true;
                    })
                .filter( (product) -> { if( filterOfProducts.getMaxUnitPrice()!=null && !filterOfProducts.getMaxUnitPrice().equals(BigDecimal.valueOf(0)) ) 
                                            return product.getUnitPrice().compareTo(filterOfProducts.getMaxUnitPrice()) <= 0;
                                        else
                                            return true;
                    })
                .sorted( (o1, o2) -> {  return o1.getCategory().compareTo( o2.getCategory() ); 
                    })
                .collect(Collectors.toList());
        model.addAttribute("allProducts", afterFilteringProducts);
        model.addAttribute("sessionId", request.getSession(true).getId());
        model.addAttribute("jspFile", "welcome");
        addAtributesToModel(model);
        return "welcome";
    }
    
    
    @RequestMapping("/product")
    public String getProductByNo(@RequestParam String productNo, Model model){
        logger.info("### getProductByNo");
        Product product;
        product = productService.getOneByProductNo(productNo); 
        product.setStatus( ProductStatus.valueOf(product.getStatus()).getDescription() );
        model.addAttribute("product", product);
        model.addAttribute("jspFile", "product");
        return "product";
    }
    
    
    @InitBinder(value = "filterOfProducts")
    public void initializeBinder(WebDataBinder binder){
        binder.setAllowedFields("manufacturer", "category", "minUnitPrice", "maxUnitPrice", "inStock", "language");
    }

    
    private Model addAtributesToModel(Model model){
        model.addAttribute("manufacturers", productService.getAllManufacturers());
        model.addAttribute("categories", productService.getAllCategories());
        return model;
    }
}