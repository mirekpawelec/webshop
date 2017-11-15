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
import pl.pawelec.webshop.model.enum_.FaqStatus;
import pl.pawelec.webshop.service.FaqService;
import pl.pawelec.webshop.utils.AtributesModel;

/**
 *
 * @author mirek
 */
@Controller
public class FaqController {
    @Autowired
    private FaqService faqService;
    Logger logger = Logger.getLogger(FaqController.class);
    
    @RequestMapping("/faq")
    public String getFaq(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("faq", faqService.getAll()
                                                                .stream()
                                                                .filter(f->f.getStatus().equals(FaqStatus.OK.name()))
                                                                .collect(Collectors.toList()));
        return "redirect:/home";
    }
    
    @RequestMapping("/admin/faq")
    public String getAllQuestionsFaq(Model model, HttpServletRequest request){
        model.addAttribute("questions", faqService.getAll());
        AtributesModel.addGlobalAtributeToModel(model, request);
        model.addAttribute("jspFile", "questionsFaq");
        return "questionsFaq";
    }
    
    @RequestMapping(value = "/admin/faq/add", method = RequestMethod.GET)
    public String addQuestionForm(Model model, HttpServletRequest request){
        model.addAttribute("modelFaq", new Faq());
        model.addAttribute("jspFile", "addUpdateQuestionFaq");
        AtributesModel.addGlobalAtributeToModel(model, request);
        addLocalAttributesToModel(model);
        return "addUpdateQuestionFaq";
    }
    
    @RequestMapping(value = "/admin/faq/{id}/update", method = RequestMethod.GET)
    public String updateQuestionForm(@PathVariable("id") String faqId, Model model, HttpServletRequest request){
        model.addAttribute("modelFaq", faqService.getOneById(Long.valueOf(faqId))); 
        model.addAttribute("jspFile", "addUpdateQuestionFaq");
        AtributesModel.addGlobalAtributeToModel(model, request);
        addLocalAttributesToModel(model);
        return "addUpdateQuestionFaq";
    }
    
    @RequestMapping(value = "/admin/faq/save", method = RequestMethod.POST)
    public String processSaveQuestionForm(@ModelAttribute("modelFaq") Faq modelFaqToBeAdd, BindingResult result, Model model, 
                                         HttpServletRequest request, final RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            AtributesModel.addGlobalAtributeToModel(model, request);
            addLocalAttributesToModel(model);
            model.addAttribute("jspFile", "addUpdateQuestionFaq");
            return "addUpdateQuestionFaq";
        }
        logger.info("Save... ["+modelFaqToBeAdd+']');
        if(modelFaqToBeAdd.isNew()){
            Long id = faqService.createAndGetId(modelFaqToBeAdd);
            redirectAttributes.addFlashAttribute("cssCreate", "success");
            redirectAttributes.addFlashAttribute("createInfo", id);
        }else{
            modelFaqToBeAdd.setLastModyficationDate(LocalDateTime.now()); 
            faqService.update(modelFaqToBeAdd);
            redirectAttributes.addFlashAttribute("cssUpdate", "success");
            redirectAttributes.addFlashAttribute("updateInfo", modelFaqToBeAdd.getFaqId());
        }
        return "redirect:/admin/faq";
    }
    
    @RequestMapping(value = "/admin/faq/{id}/delete")
    public String deleteQuestion(@PathVariable("id") String faqId, final RedirectAttributes redirectAttributes){
        if(faqService.exists(Long.valueOf(faqId))){
            faqService.deleteById(Long.valueOf(faqId));
        }
        redirectAttributes.addFlashAttribute("cssDelete", "danger");
        redirectAttributes.addFlashAttribute("deleteInfo", faqId);
        return "redirect:/admin/faq";
    }
    
    private Model addLocalAttributesToModel(Model model){
        model.addAttribute("statuses", Arrays.asList(FaqStatus.values()));
        return model;
    }   
}
