/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.validator;

import java.util.Optional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.pawelec.webshop.exception.NoSystemClassKeyFoundException;
import pl.pawelec.webshop.model.SystemClass;
import pl.pawelec.webshop.service.SystemClassService;

/**
 *
 * @author mirek
 */
public class SystemClassUniqueKeyValidator implements Validator{

    @Autowired
    private SystemClassService systemClassService; 
    
    Logger logger = Logger.getLogger(SystemClassUniqueKeyValidator.class);
    
    @Override
    public boolean supports(Class<?> type) {
        return SystemClass.class.equals(type);
    }

    @Override
    public void validate(Object validationClass, Errors errors) {
        SystemClass systemClass = (SystemClass) validationClass;
        try{
            systemClassService.getByUniqueKey(systemClass.getSymbol(), systemClass.getName());
            if(systemClass.isNew()){
                errors.rejectValue("symbol", "pl.pawelec.webshop.validator.SystemClassUniqueKeyValidator.message");
            }
        }catch(NoSystemClassKeyFoundException sce){
            logger.info("The given key do not exist");
        }
    }
    
}
