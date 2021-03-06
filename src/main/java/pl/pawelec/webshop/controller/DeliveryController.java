/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pawelec.webshop.model.Delivery;
import pl.pawelec.webshop.model.enum_.DeliveryStatus;
import pl.pawelec.webshop.service.DeliveryService;
import pl.pawelec.webshop.utils.AtributesModel;

/**
 *
 * @author mirek
 */
@Controller
@RequestMapping("/admin/deliveries")
public class DeliveryController {
    @Autowired
    private DeliveryService deliveryService;
    
    
    @RequestMapping
    public String getAllDeliveries(Model model, HttpServletRequest request){ 
        List<Delivery> deliveries = deliveryService.getAll();
        deliveries.stream().forEach( delivery -> delivery.setStatus(DeliveryStatus.valueOf(delivery.getStatus()).getDescription()) );
        model.addAttribute("deliveries", deliveries);
        model.addAttribute("jspFile", "deliveries");
        AtributesModel.addGlobalAtributeToModel(model, request);
        return "deliveries";
    }
    
}
