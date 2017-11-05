/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.pawelec.webshop.model.UserInfo;

/**
 *
 * @author mirek
 */
public class UserPasswordValidator implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return UserInfo.class.equals(type);
    }

    @Override
    public void validate(Object validationClass, Errors errors) {
        UserInfo userInfo = (UserInfo) validationClass;
        
        if(userInfo.isNew()){
            if(userInfo.getPassword().isEmpty()){
                errors.rejectValue("password", "pl.pawelec.webshop.validator.UserPsswordValidator.NotNull.message");
            } 
            if(userInfo.getRepeatPassword().isEmpty()){
                errors.rejectValue("repeatPassword", "pl.pawelec.webshop.validator.UserPsswordValidator.NotNull.message");
            }
        }
        if(!userInfo.getPassword().equals(userInfo.getRepeatPassword())){
            errors.rejectValue("repeatPassword", "pl.pawelec.webshop.validator.UserPsswordValidator.message");
        }
    }
    
}
