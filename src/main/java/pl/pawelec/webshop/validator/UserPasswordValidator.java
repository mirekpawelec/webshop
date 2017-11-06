/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.validator;

import java.util.Optional;
import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.pawelec.webshop.model.UserInfo;

/**
 *
 * @author mirek
 */
public class UserPasswordValidator implements Validator{
    Logger logger = Logger.getLogger(UserPasswordValidator.class);
    
    @Override
    public boolean supports(Class<?> type) {
        return UserInfo.class.equals(type);
    }

    @Override
    public void validate(Object validationClass, Errors errors) {
        UserInfo userInfo = (UserInfo) validationClass;
//        try{
            if(userInfo.isNew()){
                if(!Optional.ofNullable(userInfo.getPassword()).isPresent() || userInfo.getPassword().isEmpty()){
                    errors.rejectValue("password", "pl.pawelec.webshop.validator.UserPsswordValidator.NotNull.message");
                } 
                if(!Optional.ofNullable(userInfo.getRepeatPassword()).isPresent() || userInfo.getRepeatPassword().isEmpty()){
                    errors.rejectValue("repeatPassword", "pl.pawelec.webshop.validator.UserPsswordValidator.NotNull.message");
                }
            }
            if((Optional.ofNullable(userInfo.getPassword()).isPresent() && Optional.ofNullable(userInfo.getRepeatPassword()).isPresent()) 
                        && !userInfo.getPassword().equals(userInfo.getRepeatPassword())){
                errors.rejectValue("repeatPassword", "pl.pawelec.webshop.validator.UserPsswordValidator.message");
            }
//        }catch(NullPointerException ne){
//            logger.info(ne.getMessage());
//        }
    }
    
}
