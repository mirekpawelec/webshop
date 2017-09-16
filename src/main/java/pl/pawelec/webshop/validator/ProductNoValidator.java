/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.validator;

import java.util.Optional;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import pl.pawelec.webshop.exception.NoProductFoundUnderProductNoException;
import pl.pawelec.webshop.model.Product;
import pl.pawelec.webshop.service.ProductService;

/**
 *
 * @author mirek
 */
public class ProductNoValidator implements ConstraintValidator<ProductNo, String>{
    @Autowired
    private ProductService productService;
    
    @Override
    public void initialize(ProductNo constraintAnnotation) {
    }

    @Override
    public boolean isValid(String productNo, ConstraintValidatorContext context) {
        Product product = null;
        try{
            product = productService.getOneByProductNo(productNo);
        } catch (NoProductFoundUnderProductNoException e){
            return true;
        } catch (NullPointerException ne){}

        return product != null ? false : true;
    }
}
