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
import pl.pawelec.webshop.model.UserInfo;
import pl.pawelec.webshop.service.UserInfoService;

/**
 *
 * @author mirek
 */
public class UserLoginValidator implements Validator{
    @Autowired
    private UserInfoService userInfoService;
    
    @Override
    public boolean supports(Class<?> type) {
        return UserInfo.class.equals(type);
    }

    @Override
    public void validate(Object validationClass, Errors errors) {
        UserInfo userInfo, userExist;
        userInfo = userExist = new UserInfo();
        String login = "";
        
        userInfo = (UserInfo) validationClass;
        try{
            if(userInfo.isNew()){
                userExist = userInfoService.getByLogin(userInfo.getLogin());
                if(Optional.ofNullable(userExist.getUserId()).isPresent()){
                    errors.rejectValue("login", "pl.pawelec.webshop.validator.UserLoginValidator.message");
                }
            }else{
                userExist = userInfoService.getByLogin(userInfo.getLogin());
                if(!userInfo.getUserId().equals(userExist.getUserId())){
                    errors.rejectValue("login", "pl.pawelec.webshop.validator.UserLoginValidator.message");
                }
            }
        }catch(NullPointerException npe){
            System.out.println("UserLoginValidator(): It's ok! The login does not exist.");
        }
    }
    
}
