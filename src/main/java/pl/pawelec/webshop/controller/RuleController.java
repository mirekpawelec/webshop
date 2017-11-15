/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.pawelec.webshop.model.Faq;
import pl.pawelec.webshop.model.Rule;
import pl.pawelec.webshop.model.enum_.FaqStatus;
import pl.pawelec.webshop.model.enum_.RuleStatus;
import pl.pawelec.webshop.service.RuleService;
import pl.pawelec.webshop.utils.AtributesModel;

/**
 *
 * @author mirek
 */
@Controller
public class RuleController {
    @Autowired
    private RuleService ruleService;
    Logger logger = Logger.getLogger(RuleController.class);
    
    @RequestMapping("/rule")
    public String getFaq(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("contentRule", ruleService.getAll()
                                                                .stream()
                                                                .filter(f->f.getStatus().equals(FaqStatus.OK.name()))
                                                                .collect(Collectors.toList())
                                                                .get(0).getContentRule());
        return "redirect:/home";
    }
    
    @RequestMapping("/admin/rules")
    public String getAllQuestionsFaq(Model model, HttpServletRequest request){
        model.addAttribute("rules", ruleService.getAll());
        AtributesModel.addGlobalAtributeToModel(model, request);
        model.addAttribute("jspFile", "allRules");
        return "allRules";
    }
    
    @RequestMapping(value = "/admin/rule/add", method = RequestMethod.GET)
    public String addRuleForm(Model model, HttpServletRequest request){
        model.addAttribute("modelRule", new Rule());
        AtributesModel.addGlobalAtributeToModel(model, request);
        addLocalAttributesToModel(model);
        return "addUpdateRule";
    }
    
    @RequestMapping(value = "/admin/rule/{id}/update", method = RequestMethod.GET)
    public String updateRuleForm(@PathVariable("id") String ruleId, Model model, HttpServletRequest request){
        model.addAttribute("modelRule", ruleService.getOneById(Long.valueOf(ruleId))); 
        AtributesModel.addGlobalAtributeToModel(model, request);
        addLocalAttributesToModel(model);
        return "addUpdateRule";
    }
    
    @RequestMapping(value = "/admin/rule/save", method = RequestMethod.POST)
    public String processSaveRuleForm(@ModelAttribute("modelRule") Rule modelRuleToBeAdd, BindingResult result, Model model, 
                                         HttpServletRequest request, final RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            AtributesModel.addGlobalAtributeToModel(model, request);
            addLocalAttributesToModel(model);
            return "addUpdateRule";
        }
        logger.info("Save... ["+modelRuleToBeAdd+']');
        if(modelRuleToBeAdd.isNew()){
            Long id = ruleService.createAndGetId(modelRuleToBeAdd);
            redirectAttributes.addFlashAttribute("cssCreate", "success");
            redirectAttributes.addFlashAttribute("createInfo", id);
        }else{
            modelRuleToBeAdd.setLastModyficationDate(LocalDateTime.now()); 
            ruleService.update(modelRuleToBeAdd);
            redirectAttributes.addFlashAttribute("cssUpdate", "success");
            redirectAttributes.addFlashAttribute("updateInfo", modelRuleToBeAdd.getRuleId());
        }
        return "redirect:/admin/rules";
    }
    
    @RequestMapping(value = "/admin/rule/{id}/delete")
    public String deleteRule(@PathVariable("id") String ruleId, final RedirectAttributes redirectAttributes){
        if(ruleService.exists(Long.valueOf(ruleId))){
            ruleService.deleteById(Long.valueOf(ruleId));
        }
        redirectAttributes.addFlashAttribute("cssDelete", "danger");
        redirectAttributes.addFlashAttribute("deleteInfo", ruleId);
        return "redirect:/admin/rules";
    }
    
    private Model addLocalAttributesToModel(Model model){
        model.addAttribute("statuses", Arrays.asList(RuleStatus.values()));
        model.addAttribute("jspFile", "addUpdateRule");
        return model;
    }   
}