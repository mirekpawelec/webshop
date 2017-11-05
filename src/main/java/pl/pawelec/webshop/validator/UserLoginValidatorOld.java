/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.validator;

import java.util.Optional;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import pl.pawelec.webshop.model.UserInfo;
import pl.pawelec.webshop.service.UserInfoService;

/**
 *
 * @author mirek
 */
public class UserLoginValidatorOld implements ConstraintValidator<UserLogin, String>{

    @Autowired
    private UserInfoService userInfoService;
    
    @Override
    public void initialize(UserLogin constraintAnnotation) {
    }

    @Override
    public boolean isValid(String loginNo, ConstraintValidatorContext context) {
        UserInfo userInfo = null;
        try{
            userInfo = userInfoService.getByLogin(loginNo);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(userInfo).isPresent()? false : true;
    }
    
}
