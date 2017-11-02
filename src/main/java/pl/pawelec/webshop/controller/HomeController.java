/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.pawelec.webshop.model.ClientMessage;
import pl.pawelec.webshop.model.ProductFilter;
import pl.pawelec.webshop.model.Product;
import pl.pawelec.webshop.model.enum_.ProductStatus;
import pl.pawelec.webshop.service.CartService;
import pl.pawelec.webshop.service.ClientMessageService;
import pl.pawelec.webshop.service.ProductService;
import pl.pawelec.webshop.service.SystemClassService;
import pl.pawelec.webshop.utils.AtributesModel;

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
    @Autowired
    private SystemClassService systemClassService;
    @Autowired
    private ClientMessageService clientMessageService;
    private static final String SYMBOL_SUBJECT_CLIENT_MESSAGE = "subject_client_message";
    
    
    
    @RequestMapping
    public String getAllProducts(@ModelAttribute("messageForm") @Valid ClientMessage message, 
                                 BindingResult result,
                                 @ModelAttribute("filterProducts") ProductFilter filterOfProducts, 
                                 Model model, 
                                 HttpServletRequest request){
        logger.info("### getAllProducts");
        
        if(result.hasErrors() && !message.isNew()){
            AtributesModel.addGlobalAtributeToModel(model, request);
            addLocalAtributesToModel(model, request, "homepage");
            model.addAttribute("validationError", "danger");
            return "homepage";
        }
        if(result.getSuppressedFields().length > 0){
            throw new RuntimeException("Próba wiązania niedozwolonych pól: " 
                            + StringUtils.arrayToCommaDelimitedString(result.getSuppressedFields()));
        }
        if(!message.isNew()){
            String selectedSubjectMessage = systemClassService.getBySymbol(SYMBOL_SUBJECT_CLIENT_MESSAGE).stream()
                                                        .filter(sc->sc.getValue().equals(message.getSubject()))
                                                        .map(sc->sc.getName()).findFirst().orElse("NONE");
            clientMessageService.create( new ClientMessage(message.getName(), message.getEmail(), selectedSubjectMessage, message.getContent()));
            model.addAttribute("messageForm", new ClientMessage());
            model.addAttribute("successMsgSent", "success");
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
        AtributesModel.addGlobalAtributeToModel(model, request);
        addLocalAtributesToModel(model, request, "homepage");
        return "homepage";
    }
    
    @RequestMapping("/product")
    public String getProductByNo(@RequestParam String productNo, Model model, HttpServletRequest request){
        logger.info("### getProductByNo");
        Product product;
        product = productService.getOneByProductNo(productNo); 
        product.setStatus( ProductStatus.valueOf(product.getStatus()).getDescription() );
        model.addAttribute("product", product);
        AtributesModel.addGlobalAtributeToModel(model, request);
        addLocalAtributesToModel(model, request, "product");
        return "product";
    }
    

    public void initializeBinder(WebDataBinder binder){
        binder.setAllowedFields("manufacturer", "category", "minUnitPrice", "maxUnitPrice", "inStock", "language", "loggedInUser", "role", "subject", "name", "content", "email");
//        binder.setValidator(clientMessageValidator);
    }

    private Model addLocalAtributesToModel(Model model, HttpServletRequest request, String namePage){
        model.addAttribute("manufacturers", productService.getAllManufacturers());
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("sessionId", request.getSession(true).getId());
        model.addAttribute("jspFile", namePage);
        model.addAttribute("subjectMessage", systemClassService.getBySymbol(SYMBOL_SUBJECT_CLIENT_MESSAGE));
        return model;
    }
    
}