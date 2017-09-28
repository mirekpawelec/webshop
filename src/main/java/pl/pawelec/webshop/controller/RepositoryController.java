/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.controller;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pawelec.webshop.model.enum_.ProductStatus;
import pl.pawelec.webshop.model.Repository;
import pl.pawelec.webshop.service.RepositoryService;

/**
 *
 * @author mirek
 */
@Controller
@RequestMapping("/admin/repository")
public class RepositoryController {
    
    @Autowired
    private RepositoryService repositoryService;
    
    private Logger logger = Logger.getLogger(RepositoryController.class);
    
    @RequestMapping
    public String getWholeStock(@ModelAttribute("wholeStock") Repository wholeStock, Model model){
        logger.info("### getWholeStock");
        model.addAttribute("wholeStock", repositoryService.getAll());
        model.addAttribute("jspFile", "warehouse");
        return "warehouse";
    }
}
