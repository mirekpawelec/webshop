/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webstore.test;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import pl.pawelec.webstore.model.Product;

/**
 *
 * @author mirek
 */
public class tests {
    
    public static void main(String[] args) {
        
        Product product = new Product.Builder().withProductNo("123.456.78").build();
        System.out.println(product);
        
        ResourceBundleMessageSource rbm = new ResourceBundleMessageSource();
        rbm.setBasename("messages");
        
        LocalValidatorFactoryBean lvfb = new LocalValidatorFactoryBean();
        lvfb.setValidationMessageSource(rbm);

        
    }
}
