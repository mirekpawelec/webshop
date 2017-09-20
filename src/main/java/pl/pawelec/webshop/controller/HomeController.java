/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
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
import pl.pawelec.webshop.model.FilterProduct;
import pl.pawelec.webshop.model.Product;
import pl.pawelec.webshop.model.ProductStatus;
import pl.pawelec.webshop.service.ProductService;

/**
 *
 * @author mirek
 */
@RequestMapping("/home")
@Controller
public class HomeController {
    
    private Logger logger = Logger.getLogger(HomeController.class);
    
    @Autowired
    private ProductService productService;
    
    @RequestMapping
    public String getAllProducts(Model model, HttpServletRequest request){
        logger.info("### getAllProducts");
        List<Product> afterFilteringProducts = new ArrayList<Product>(); 
        
//        request.getParameterMap().entrySet().forEach((m) -> System.out.println("l:key=" + m.getKey() + " , l:value=" + Arrays.toString(m.getValue())));
//        afterFilteringProducts = productService.getAll();
//        afterFilteringProducts.forEach(System.out::println);
        
        afterFilteringProducts = productService.getAll().parallelStream()
                .filter( (product) ->  {if(request.getParameter("manufacturer")!=null && !request.getParameter("manufacturer").equals("NONE")) 
                                            return product.getManufacturer().equals(request.getParameter("manufacturer")); 
                                        else 
                                            return true; 
                        })
                .filter( (product) ->  {if(request.getParameter("category")!=null && !request.getParameter("category").equals("NONE")) 
                                            return product.getCategory().equals(request.getParameter("category"));
                                        else
                                            return true;
                        })
                .filter( (product) ->  {if(request.getParameter("minUnitPrice")!=null && !request.getParameter("minUnitPrice").isEmpty()) 
                                            return product.getUnitPrice().compareTo(new BigDecimal(request.getParameter("minUnitPrice"))) >= 0;
                                        else
                                            return true;
                        })
                .filter( (product) ->  {if(request.getParameter("maxUnitPrice")!=null && !request.getParameter("maxUnitPrice").isEmpty()) 
                                            return product.getUnitPrice().compareTo(new BigDecimal(request.getParameter("maxUnitPrice"))) <= 0;
                                        else
                                            return true;
                        })
                .collect( Collectors.toList() );
        
//        afterFilteringProducts.forEach(System.out::println);
        
        if(request.getParameter("manufacturer") != null && !request.getParameter("manufacturer").equals("NONE")){
            model.addAttribute("manufacturerSelected", request.getParameter("manufacturer"));
        }
        if(request.getParameter("category") != null && !request.getParameter("category").equals("NONE")){
            model.addAttribute("categoriesSelected", request.getParameter("category"));
        }
        if(request.getParameter("minUnitPrice")!=null && !request.getParameter("minUnitPrice").isEmpty()){
            model.addAttribute("minPriceEntered", request.getParameter("minUnitPrice"));
        }
        if(request.getParameter("maxUnitPrice")!=null && !request.getParameter("maxUnitPrice").isEmpty()){
            model.addAttribute("maxPriceEntered", request.getParameter("maxUnitPrice"));
        }
        model.addAttribute("filterProducts",  new FilterProduct());
        model.addAttribute("allProducts", afterFilteringProducts);
        model.addAttribute("jspFile", "welcome");
        addAtributesToModel(model);
        return "welcome";
    }
    
//    @RequestMapping("/filter")
//    public String processFilterProducts(@ModelAttribute FilterProduct filterProducts, Model model, BindingResult result){
//        logger.info("### processFilterProducts");
//        if(result.getSuppressedFields().length > 0) throw new RuntimeException("Próba wiązania niedozwolonych pól" + StringUtils.arrayToCommaDelimitedString(result.getSuppressedFields()));
//        addAtributesToModel(model);
//        model.addAttribute("jspFile", "welcome");
//        return "redirect: home/welcome";
//    }
    
    @RequestMapping("/product")
    public String getProductByNo(@RequestParam String productNo, Model model){
        logger.info("### getProductByNo");
        
        Product product;
        product = productService.getOneByProductNo(productNo); 

        product.setStatus( ProductStatus.valueOf(product.getStatus()).getProductStatusDescription() );
        
        model.addAttribute("product", product);
        model.addAttribute("jspFile", "product");
        return "product";
    }
    
    @InitBinder(value = "filterProducts")
    public void initializeBinder(WebDataBinder binder){
        binder.setAllowedFields("manufacturer", "category", "minUnitPrice", "maxUnitPrice");
    }

    private Model addAtributesToModel(Model model){
        model.addAttribute("manufacturers", productService.getAllManufacturers());
        model.addAttribute("categories", productService.getAllCategories());
        return model;
    }
}