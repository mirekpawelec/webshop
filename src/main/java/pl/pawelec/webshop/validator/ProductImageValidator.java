/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import pl.pawelec.webshop.model.Product;

/**
 *
 * @author mirek
 */
public class ProductImageValidator implements Validator{

    private Long sizeImage;

    public void setSizeImage(Long sizeImage) {
        this.sizeImage = sizeImage;
    }
    
    @Override
    public boolean supports(Class<?> type) {
        return Product.class.equals(type);
    }

    @Override
    public void validate(Object validatedClass, Errors errors) {
        Product product = (Product) validatedClass;
        if(!product.getProductImage().isEmpty() && product.getProductImage().getSize() > sizeImage ){
            errors.rejectValue("productImage", "pl.pawelec.webshop.validator.ProductImageValidator.message");
        }
    }
    
}
