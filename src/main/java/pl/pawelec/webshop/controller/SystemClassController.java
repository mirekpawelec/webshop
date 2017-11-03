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
import pl.pawelec.webshop.model.SystemClass;
import pl.pawelec.webshop.service.SystemClassService;
import pl.pawelec.webshop.utils.AtributesModel;
import pl.pawelec.webshop.validator.SystemClassValidator;

/**
 *
 * @author mirek
 */
@SessionAttributes(names = {"jspFile", "updateInfo"})
@Controller
@RequestMapping("admin/classes")
public class SystemClassController {
    Logger logger = Logger.getLogger(SystemClassController.class);
    @Autowired
    private SystemClassService systemClassService;
    @Autowired
    private SystemClassValidator systemClassValidator;
    
    
    
    @RequestMapping
    public String getAllClass(Model model, HttpServletRequest request){
        model.addAttribute("systemClasses", systemClassService.getAll());
        model.addAttribute("jspFile", "systemClasses");
        AtributesModel.addGlobalAtributeToModel(model, request);
        return "systemClasses";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addClass(Model model, HttpServletRequest request){
        model.addAttribute("newSystemClass", new SystemClass());
        model.addAttribute("jspFile", "addSystemClass");
        AtributesModel.addGlobalAtributeToModel(model, request);
        return "addSystemClass";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddClass(@ModelAttribute("newSystemClass") @Valid SystemClass systemClassToBeAdd, BindingResult result
                                  , Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request){
        if(result.hasErrors()){
            AtributesModel.addGlobalAtributeToModel(model, request);
            return "addSystemClass";
        }
        String[] suppresedFields = result.getSuppressedFields();
        if(suppresedFields.length > 0){
            throw new RuntimeException("It has occurred an attempt bind the illegal fields:" + StringUtils.arrayToCommaDelimitedString(suppresedFields));
        }
        logger.info("Save a new class...");
        systemClassService.create(systemClassToBeAdd);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("create", true);
        redirectAttributes.addFlashAttribute("createInfo", systemClassToBeAdd.getSymbol()+" - "+systemClassToBeAdd.getName());
        return "redirect:/admin/classes";
    }
    
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String updateClass(@PathVariable("id") String classId, Model model, HttpServletRequest request){
        SystemClass systemClass = systemClassService.getOneById(Long.valueOf(classId));
        model.addAttribute("updateSystemClass", systemClass);
        model.addAttribute("updateInfo", systemClass.getSymbol()+" - "+systemClass.getName());
        model.addAttribute("jspFile", "updateSystemClass");
        AtributesModel.addGlobalAtributeToModel(model, request);
        return "updateSystemClass"; 
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateClass(@ModelAttribute("updateSystemClass") @Valid SystemClass systemClassToBeUpdate, BindingResult result
                                     , final RedirectAttributes redirectAttributes, Model model, HttpServletRequest request){
        if(result.hasErrors()){
            AtributesModel.addGlobalAtributeToModel(model, request);
            return "updateSystemClass";
        }
        systemClassToBeUpdate.setLastModificationDate(LocalDateTime.now());
        logger.info("Save...["+systemClassToBeUpdate+']');
        systemClassService.update(systemClassToBeUpdate);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("update", true);
        redirectAttributes.addFlashAttribute("updateInfo", systemClassToBeUpdate.getSymbol()+" - "+systemClassToBeUpdate.getName());
        return "redirect:/admin/classes";
    }
    
    @RequestMapping("/{id}/delete")
    public String deleteClass(@PathVariable("id") String classId, final RedirectAttributes redirectAttributes){
        SystemClass systemClass = systemClassService.getOneById(Long.valueOf(classId));
        systemClassService.deleteById(Long.valueOf(classId));
        redirectAttributes.addFlashAttribute("css", "danger");
        redirectAttributes.addFlashAttribute("delete", true);
        redirectAttributes.addFlashAttribute("deleteInfo", systemClass.getSymbol()+" - "+systemClass.getName());
        return "redirect:/admin/classes";
    }
 
    @InitBinder(value = "newSystemClass")
    private void newClassBinder(WebDataBinder binder){
        binder.setDisallowedFields("classId","lastModyficationDate","createDate");
        binder.setValidator(systemClassValidator);
    }
    
    @InitBinder(value = "updateSystemClass")
    private void updateClassBinder(WebDataBinder binder){
        binder.setValidator(systemClassValidator);
    }
}
