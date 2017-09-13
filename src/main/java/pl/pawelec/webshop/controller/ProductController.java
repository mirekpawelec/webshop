/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import pl.pawelec.webshop.model.Product;
import pl.pawelec.webshop.model.ProductStatus;
import pl.pawelec.webshop.service.ProductService;
import pl.pawelec.webshop.model.Product.addForm;
import pl.pawelec.webshop.model.Product.modifyForm;
import pl.pawelec.webshop.validator.ProductValidator;


/**
 *
 * @author mirek
 */
@SessionAttributes(names = {"productStatus", "productNumber"})
@RequestMapping(value = "/products")
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private ProductValidator productValidator;
    
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
    
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modyfyProductByIdForm(@RequestParam("id") String productId, Model model, HttpServletRequest request){
        System.out.println("### modify product controller (GET)");
        Product modifyProduct = productService.getOneById(Long.valueOf(productId));
        model.addAttribute("productNumber", modifyProduct.getProductNo());
        model.addAttribute("modifyProduct", modifyProduct);
        return "modifyProduct";
    }
    
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String processModyfyProductByIdForm(@RequestParam("id") String productId, 
                                               @ModelAttribute("modifyProduct") @Validated({modifyForm.class}) Product productToBeModify, 
                                               BindingResult result, HttpServletRequest request){
        System.out.println("### process modify product controller (POST)");
        if(result.hasErrors()) return "modifyProduct";
        
        String[] suppresedFields = result.getSuppressedFields();
        if(suppresedFields.length > 0) throw new RuntimeException("Próba wiązania niedozwolonych pól: " + StringUtils.arrayToCommaDelimitedString(suppresedFields));
        
        productToBeModify.setProductId(Long.valueOf(productId));
        productToBeModify.setProductNo((String) request.getSession().getAttribute("productNumber"));
        System.out.println("### Modify: " + productToBeModify);
        productService.update(productToBeModify);
        
        return "redirect:/products";
    }
    
    @RequestMapping("/delete")
    public String deleteProductById(@RequestParam("id") String productId, Model model){
        System.out.println("### delete product controller");
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
    public String processAddProductForm(@ModelAttribute("newProduct") @Validated({addForm.class}) Product productToBeAdd, BindingResult result, HttpServletRequest request){
        System.out.println("### process add new product controller (POST)");  

//        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
//        Set<ConstraintViolation<Product>> violations = validatorFactory.getValidator().validate(productToBeAdd, addForm.class);
//        for (ConstraintViolation<Product> violation : violations) {
//            String propertyPath = violation.getPropertyPath().toString();
//            String message = violation.getMessage();
//            System.out.println("invalid value for: '" + propertyPath + "': " + message);
//        }

        if(result.hasErrors()) return "addProduct";
        
        String[] suppresedFields = result.getSuppressedFields();
        if(suppresedFields.length > 0) throw new RuntimeException("Próba wiązania niedozwolonych pól: " + StringUtils.arrayToCommaDelimitedString(suppresedFields));
        
        MultipartFile productImage, productUserManual;
        String mainPath = request.getSession().getServletContext().getRealPath("");
        
        File createFolderImage = new File(mainPath+"resources\\img\\");
        if(!createFolderImage.isDirectory())
            createFolderImage.mkdirs();
        productImage = productToBeAdd.getProductImage();
        if(productImage != null && !productImage.isEmpty()){
            try {
                productImage.transferTo(new File(createFolderImage.getAbsolutePath()+"\\"+ productToBeAdd.getProductNo() + ".jpg"));
            } catch (IOException ex) {
                throw new RuntimeException("Błąd zapisu obrazka!");
            } 
        }
        
        File createFolderPdf = new File(mainPath+"resources\\pdf\\");
        if(!createFolderPdf.isDirectory())
            createFolderPdf.mkdirs();
        productUserManual = productToBeAdd.getProductUserManual();
        if(productUserManual != null & !productUserManual.isEmpty()){
            try {
                productUserManual.transferTo(new File(createFolderPdf.getAbsolutePath()+"\\"+productToBeAdd.getProductNo()+".pdf"));
            } catch (IOException ex) {
                throw new RuntimeException("Błąd zapisu pliku pdf!");
            }
        }
        
        System.out.println("Zapisuje: " + productToBeAdd);
        productService.create(productToBeAdd);
        
        return "redirect:/products";
    }

    @InitBinder(value = "newProduct")
    public void newProductInitializeBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("productId", "createDate");
        webDataBinder.setValidator(productValidator);
    }
    
    @InitBinder(value = "modifyProduct")
    public void modyfyProductInitializeBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("productId", "productNo", "productImage", "productUserManual", "createDate");
    }
    
    @ModelAttribute("productStatus")
    public List<ProductStatus> getAllProductStatus(){
        return Arrays.asList(ProductStatus.values());
    }
    
}
