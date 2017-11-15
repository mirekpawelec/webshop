/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.validator;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.pawelec.webshop.model.Faq;
import pl.pawelec.webshop.service.FaqService;

/**
 *
 * @author mirek
 */
public class QuestionFaqValidator implements Validator{
    @Autowired
    private FaqService faqService;
    
    
    @Override
    public boolean supports(Class<?> type) {
        return Faq.class.equals(type);
    }

    @Override
    public void validate(Object validationClass, Errors errors) {
        Faq faq = (Faq) validationClass;
        if(faq.isNew()){
            if( faqService.getAll().stream().anyMatch(q->q.getQuestion().equals(faq.getQuestion())) ){
                errors.rejectValue("question", "pl.pawelec.webshop.validator.QuestionFaqValidator.message");
            }
        }else{
            Faq existElementFaq = faqService.getAll().stream().filter(q->q.getQuestion().equals(faq.getQuestion())).findFirst().orElse(new Faq());
            if(Optional.ofNullable(existElementFaq.getFaqId()).isPresent() && !existElementFaq.getFaqId().equals(faq.getFaqId())){
                errors.rejectValue("question", "pl.pawelec.webshop.validator.QuestionFaqValidator.message");
            }
        }
    }
    
}
