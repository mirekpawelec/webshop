/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.controller;

import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.pawelec.webshop.model.AppParameter;
import pl.pawelec.webshop.utils.AtributesModel;
import pl.pawelec.webshop.service.AppParameterService;
import pl.pawelec.webshop.validator.AppParameterValidator;

/**
 *
 * @author mirek
 */
@SessionAttributes(names = {"jspFile", "updateInfo"})
@Controller
@RequestMapping("admin/parameters")
public class AppParameterController {
    Logger logger = Logger.getLogger(AppParameterController.class);
    @Autowired
    private AppParameterService appParameterService;
    @Autowired
    private AppParameterValidator appParameterValidator;

    
    @RequestMapping
    public String getAllAppParameters(Model model, HttpServletRequest request){
        model.addAttribute("allParameters", appParameterService.getAll());
        model.addAttribute("jspFile", "appParameters");
        AtributesModel.addGlobalAtributeToModel(model, request);
        return "appParameters";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addAppParameter(Model model, HttpServletRequest request){
        model.addAttribute("newParameterForm", new AppParameter());
        model.addAttribute("jspFile", "addAppParameter");
        AtributesModel.addGlobalAtributeToModel(model, request);
        return "addAppParameter";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddAppParameter(@ModelAttribute("newParameterForm") @Valid AppParameter appParameterToBeAdd, BindingResult result,
                                  Model model, HttpServletRequest request, final RedirectAttributes redirectAttributes ){
        if(result.hasErrors()){
            AtributesModel.addGlobalAtributeToModel(model, request);
            return "addAppParameter";
        }
        String[] suppresedFields = result.getSuppressedFields();
        if(suppresedFields.length > 0){
            throw new RuntimeException("It has occurred an attempt bind the illegal fields:" + StringUtils.arrayToCommaDelimitedString(suppresedFields));
        }
        logger.info("Save... ["+appParameterToBeAdd+']');
        appParameterService.create(appParameterToBeAdd);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("create", true);
        redirectAttributes.addFlashAttribute("createInfo", appParameterToBeAdd.getSymbol()+" - "+appParameterToBeAdd.getName());
        return "redirect:/admin/parameters";
    }
    
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String updateAppParameter(@PathVariable("id") String appParameterId, Model model, HttpServletRequest request){
        AppParameter appParameter = appParameterService.getOneById(Long.valueOf(appParameterId));
        model.addAttribute("updateParameterForm", appParameter);
        model.addAttribute("updateInfo", appParameter.getSymbol()+" - "+appParameter.getName());
        model.addAttribute("jspFile", "updateAppParameter");
        AtributesModel.addGlobalAtributeToModel(model, request);
        return "updateAppParameter"; 
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateAppParameter(@ModelAttribute("updateParameterForm") @Valid AppParameter appParameterToBeUpdate, BindingResult result,
                                     Model model, HttpServletRequest request, final RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            AtributesModel.addGlobalAtributeToModel(model, request);
            model.addAttribute("jspFile", "updateAppParameter");
            return "updateAppParameter";
        }
        appParameterToBeUpdate.setLastModificationDate(LocalDateTime.now());
        logger.info("Save... ["+appParameterToBeUpdate+']');
        appParameterService.update(appParameterToBeUpdate);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("update", true);
        redirectAttributes.addFlashAttribute("updateInfo", appParameterToBeUpdate.getSymbol()+" - "+appParameterToBeUpdate.getName());
        return "redirect:/admin/parameters";
    }
    
    @RequestMapping("/{id}/delete")
    public String deleteAppParameter(@PathVariable("id") String appParameterId, final RedirectAttributes redirectAttributes){
        AppParameter appParameter = appParameterService.getOneById(Long.valueOf(appParameterId));
        appParameterService.delete(appParameter);
        redirectAttributes.addFlashAttribute("css", "danger");
        redirectAttributes.addFlashAttribute("delete", true);
        redirectAttributes.addFlashAttribute("deleteInfo", appParameter.getSymbol()+" - "+appParameter.getName());
        return "redirect:/admin/parameters";
    }
 
    @InitBinder(value = {"newParameterForm"})
    public void addAppParameterBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("parameterId", "lastModificationDate", "createDate");
        webDataBinder.setValidator(appParameterValidator);
    }
    
    @InitBinder(value = {"updateParameterForm"})
    public void updateAppParameterBinder(WebDataBinder webDataBinder){
        webDataBinder.setValidator(appParameterValidator);
    }
}
