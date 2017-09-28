/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.converter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.pawelec.webshop.model.Delivery;
import pl.pawelec.webshop.service.DeliveryService;

/**
 *
 * @author mirek
 */
public class DeliveryIdToDeliveryConverter implements Converter<Object, Delivery>{
    
    private Logger logger = Logger.getLogger(DeliveryIdToDeliveryConverter.class);
    
    @Autowired
    private DeliveryService deliveryService;
    
    @Override
    public Delivery convert(Object element) {
        logger.info("Konwertuje...");
        Long id = (Long) element;
        Delivery delivery = deliveryService.getOneById(id);
        logger.info("Zakończono konwersję elementu nr " + element.toString() + " na " + delivery);
        return delivery;
    }
    
}
