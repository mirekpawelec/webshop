/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.pawelec.webshop.model.UserInfo;
import pl.pawelec.webshop.model.enum_.RoleUser;
import pl.pawelec.webshop.service.UserInfoService;
import pl.pawelec.webshop.utils.AtributesModel;
import pl.pawelec.webshop.validator.UserInfoValidator;

/**
 *
 * @author mirek
 */
@SessionAttributes(names = {"loggedInUser","role"})
@Controller
public class LoginController {
    private Logger logger = Logger.getLogger(ProductController.class);
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserInfoValidator userInfoValidator;
    
    
    
    @RequestMapping(value = "/*")
    public String homepage(){
        return "redirect:/home";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, HttpServletRequest request){
        model.addAttribute("jspFile", "login");
        AtributesModel.addGlobalAtributeToModel(model, request);
        addLocalAtributesToModel(model);
        return "login";
    }
    
    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    public String createUser(Model model, HttpServletRequest request){
        model.addAttribute("newUser", new UserInfo());
        model.addAttribute("jspFile", "newUser");
        AtributesModel.addGlobalAtributeToModel(model, request);
        addLocalAtributesToModel(model);
        return "addUser"; 
    }
    
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String processCreateUser(@ModelAttribute("newUser") @Valid UserInfo userInfoToBeAdd
                                    , BindingResult result, Model model, HttpServletRequest request){
        if(result.hasErrors()){
            AtributesModel.addGlobalAtributeToModel(model, request);
            addLocalAtributesToModel(model);
            return "addUser";
        }
        String[] suppresedFields = result.getSuppressedFields();
        if(suppresedFields.length > 0){
            throw new RuntimeException("It has occurred an attempt bind the illegal fields: " 
                                + StringUtils.arrayToCommaDelimitedString(suppresedFields));
        }
        BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
        userInfoToBeAdd.setPassword(encrypt.encode(userInfoToBeAdd.getPassword()));
        logger.info("Save user: " + userInfoToBeAdd);
        userInfoService.create(userInfoToBeAdd);
        return "redirect:/login"; 
    }
    
    @InitBinder(value = "newUser")
    public void updateProductBinder(WebDataBinder webDataBinder){
        webDataBinder.setAllowedFields("login", "password", "repeatPassword", "firstName", "lastName", "email", "role");
        webDataBinder.setValidator(userInfoValidator); 
    }
    
    private Model addLocalAtributesToModel(Model model){
        model.addAttribute("role", Arrays.asList(RoleUser.values()));
        return model;
    }
}
