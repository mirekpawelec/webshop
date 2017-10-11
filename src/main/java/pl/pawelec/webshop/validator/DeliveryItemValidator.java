/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.validation.ValidationContext;
import org.springframework.stereotype.Component;
import pl.pawelec.webshop.exception.NoLoadunitNoException;
import pl.pawelec.webshop.exception.NoProductIdFoundException;
import pl.pawelec.webshop.model.DeliveryItem;
import pl.pawelec.webshop.model.Product;
import pl.pawelec.webshop.service.DeliveryItemService;
import pl.pawelec.webshop.service.ProductService;

/**
 *
 * @author mirek
 */
@Component
public class DeliveryItemValidator {
    
    Logger logger = Logger.getLogger(DeliveryItemValidator.class); 
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private DeliveryItemService deliveryItemService; 
    
    public void validateAddItemToDelivery(DeliveryItem deliveryItem, ValidationContext context) {
        
        DeliveryItem item = null;
        Product product = null;
        
        try{
            item = deliveryItemService.getByLoadunitNo(deliveryItem.getLoadunitNo());
            logger.info("Not unique! The loadunit number is used.");
        }catch(NoLoadunitNoException nl){
            logger.info("OK! The loadunit number is not used.");
        }
        try{
            product = productService.getOneById(deliveryItem.getProduct().getProductId());
            logger.info("OK! The product exists.");
        } catch (NoProductIdFoundException np){
            logger.info("Not correct! The product doesn't exist.");
        }
        
        MessageContext messages = context.getMessageContext();
        if(item!=null && deliveryItem.getItemId()==null){
            messages.addMessage(new MessageBuilder().error().source("loadunitNo")
                       .code("pl.pawelec.webshop.validator.DeliveryItemValidator.loadunitNo.message").build());
        }
        if(product==null){
            messages.addMessage(new MessageBuilder().error().source("product.productId")
                       .code("pl.pawelec.webshop.validator.DeliveryItemValidator.productId.message").build());
        }
        
    }
    
}
