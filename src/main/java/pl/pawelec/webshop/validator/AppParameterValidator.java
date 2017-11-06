/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.validator;

import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.pawelec.webshop.model.AppParameter;

/**
 *
 * @author mirek
 */
public class AppParameterValidator implements Validator{
    
    @Autowired
    private javax.validation.Validator beanValidator;
    private Set<Validator> springValidators; 
    Logger logger = Logger.getLogger(AppParameterValidator.class);
    
    public AppParameterValidator() {
        springValidators = new HashSet<Validator>();
    }
    public void setSpringValidators(Set<Validator> springValidators) {
        this.springValidators = springValidators;
    }
    
    @Override
    public boolean supports(Class<?> type) {
        return AppParameter.class.equals(type);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Set<ConstraintViolation<Object>> constraintViolations = beanValidator.validate(target);
        for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
            String propertyPath = constraintViolation.getPropertyPath().toString();
            String message = constraintViolation.getMessage();
            errors.rejectValue(propertyPath, "", message);
        }

        for(Validator validator: springValidators) {   
            validator.validate(target, errors);
        }
    }
}
