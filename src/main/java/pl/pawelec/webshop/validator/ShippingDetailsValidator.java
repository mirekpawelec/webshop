/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.validator;

import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.validation.ValidationContext;
import org.springframework.stereotype.Component;
import pl.pawelec.webshop.model.ShippingDetails;

/**
 *
 * @author mirek
 */
@Component
public class ShippingDetailsValidator {
    
    public void validateCollectShippingDetails(ShippingDetails shippingDetails, ValidationContext context){
        MessageContext messages = context.getMessageContext();
        if(shippingDetails.getPaymentMethod().equals("NONE")){
            messages.addMessage(new MessageBuilder().error().source("paymentMethod")
                       .code("pl.pawelec.webshop.validator.ShippingDetailsValidator.paymentMethod.message").build());
        }
        if(shippingDetails.getDeliveryMethod().equals("NONE")){
            messages.addMessage(new MessageBuilder().error().source("deliveryMethod")
                       .code("pl.pawelec.webshop.validator.ShippingDetailsValidator.deliveryMethod.message").build());
        }
    }
    
}
