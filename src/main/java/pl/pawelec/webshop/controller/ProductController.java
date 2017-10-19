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
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.pawelec.webshop.model.Product;
import pl.pawelec.webshop.model.Product.newForm;
import pl.pawelec.webshop.model.Product.updateForm;
import pl.pawelec.webshop.model.enum_.ProductStatus;
import pl.pawelec.webshop.service.ProductService;
import pl.pawelec.webshop.validator.ProductValidator;


/**
 *
 * @author mirek
 */
@SessionAttributes(names = {"productStatus", "productId", "productNumber"})
@RequestMapping(value = "/admin/products")
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private ProductValidator productValidator;
    
    private Logger logger = Logger.getLogger(ProductController.class);
    
    @RequestMapping
    public String allProducts(Model model){
        logger.info("### allProducts");
        
        List<Product> products = productService.getAll();
        products.stream().forEach(p -> p.setStatus(ProductStatus.valueOf(p.getStatus()).getDescription()));
        
        model.addAttribute("products", products);
        model.addAttribute("jspFile", "products");
        
        return "products";
    }
    
    
    @RequestMapping("/product")
    public String getProductById(@RequestParam("id") String productId, Model model){
        logger.info("### getProductById");
        
        Product product;
        String regex = "[0-9]{3}[.]{1}[0-9]{3}[.]{1}[0-9]{2}";
        if(productId.matches(regex)){
            product = productService.getOneByProductNo(productId); 
        }
        else {
            product = productService.getOneById(Long.valueOf(productId)); 
        }
        product.setStatus(ProductStatus.valueOf(product.getStatus()).getDescription());
        
        model.addAttribute("product", product);
        model.addAttribute("jspFile", "product");
        
        return "product";
    }
    
    
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String updateProductForm(@PathVariable("id") String productId, Model model){
        logger.info("### updateProductForm");
        
        Product updateProduct = productService.getOneById(Long.valueOf(productId));
        model.addAttribute("updateProductForm", updateProduct);
        model.addAttribute("productId", updateProduct.getProductId());
        model.addAttribute("productNumber", updateProduct.getProductNo());
        model.addAttribute("jspFile", "updateProductForm");
        
        return "updateProductForm";
    }
    
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateProductForm(@ModelAttribute("updateProductForm") @Validated({updateForm.class}) Product productToBeUpdate, 
                                               BindingResult result, HttpServletRequest request, final RedirectAttributes redirect){
        logger.info("### processUpdateProductForm");
        if(result.hasErrors()){
            return "updateProductForm";
        }
        
        String[] suppresedFields = result.getSuppressedFields();
        if(suppresedFields.length > 0){
            throw new RuntimeException("It has occurred an attempt bind the illegal fields: " + StringUtils.arrayToCommaDelimitedString(suppresedFields));
        }
        
        productToBeUpdate.setProductId((Long) request.getSession().getAttribute("productId"));
        productToBeUpdate.setProductNo((String) request.getSession().getAttribute("productNumber"));
        
        System.out.println("### Update: " + productToBeUpdate);
        productService.update(productToBeUpdate);
        
        redirect.addFlashAttribute("typeProcess", "update");
        redirect.addFlashAttribute("css", "success");
        
        return "redirect:/admin/products/product?id=" + productToBeUpdate.getProductNo() ;
    }
    
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addProductForm(Model model, HttpServletRequest request){
        logger.info("### addProductForm");
        
        Product product = new Product();
        model.addAttribute("newProductForm", product);
        model.addAttribute("jspFile", "newProductForm");
        
        return "newProductForm";
    }

            
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddProductForm(@ModelAttribute("newProductForm") @Validated({newForm.class}) Product productToBeAdd, 
                                                BindingResult result, HttpServletRequest request, final RedirectAttributes redirect){
        logger.info("### processAddProductForm"); 

        if(result.hasErrors()){
            return "newProductForm";
        }

        String[] suppresedFields = result.getSuppressedFields();
        if(suppresedFields.length > 0){
            throw new RuntimeException("It has occurred an attempt bind the illegal fields: " + StringUtils.arrayToCommaDelimitedString(suppresedFields));
        }
        
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
                throw new RuntimeException("It's has occurred an error while saving the image!");
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
                throw new RuntimeException("It's has occurred an error while saving the pdf file!");
            }
        }
        
        System.out.println("Save: " + productToBeAdd);
        productService.create(productToBeAdd);
        
        redirect.addFlashAttribute("typeProcess", "create");
        redirect.addFlashAttribute("css", "success");
        
        return "redirect:/admin/products/product?id=" + productToBeAdd.getProductNo() ;
    }

    
    @InitBinder(value = "newProductForm")
    public void newProductBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("productId", "createDate");
        webDataBinder.setValidator(productValidator);
    }
    
    
    @InitBinder(value = "updateProductForm")
    public void updateProductBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("productId", "productNo", "productImage", "productUserManual", "createDate");
    }
    
    
    @RequestMapping(value = "/{params}/delete")
    public String deleteProductById(@MatrixVariable(pathVar = "params") Map<String, List<String>> paramList, Model model, final RedirectAttributes redirect){
        logger.info("### deleteProductById");
        Long deleteId = Long.parseLong(paramList.get("id").get(0));
        String deleteProductNo = paramList.get("productNo").get(0);
        
        productService.deleteById( deleteId );
        
        redirect.addFlashAttribute("css", "success");
        redirect.addFlashAttribute("success", true);
        redirect.addFlashAttribute("deletedProductNo", deleteProductNo);

        return "redirect:/admin/products";
    }
    
    
    @ModelAttribute("productStatus")
    public List<ProductStatus> getAllProductStatus(){
        return Arrays.asList(ProductStatus.values());
    }
    
}
