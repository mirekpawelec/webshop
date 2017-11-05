/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.controller;

import java.util.Arrays;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.pawelec.webshop.model.UserDetailsAdapter;
import pl.pawelec.webshop.model.UserInfo;
import pl.pawelec.webshop.model.enum_.RoleUser;
import pl.pawelec.webshop.model.enum_.UserStatus;
import pl.pawelec.webshop.service.UserInfoService;
import pl.pawelec.webshop.utils.AtributesModel;
import pl.pawelec.webshop.validator.UserInfoValidator;

/**
 *
 * @author mirek
 */
@SessionAttributes(names = {"loggedInUser"})
@Controller
public class LoginController {
    private Logger logger = Logger.getLogger(ProductController.class);
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserInfoValidator userInfoValidator;
    
    
    
    @RequestMapping(value = "/*")
    public String homepage(HttpServletRequest request){
//        System.out.println("User=" + request.getRemoteUser());
//        System.out.println("Principal name=" + SecurityContextHolder.getContext().getAuthentication().getName());
//        System.out.println("Principal principal=" + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if(Optional.ofNullable(request.getRemoteUser()).isPresent()){
            UserInfo loginUser = userInfoService.getByLogin(request.getRemoteUser());
            loginUser.setLastLoginDate( ((UserDetailsAdapter) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getLoginDate() );
            userInfoService.update(loginUser);
        }
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
        model.addAttribute("modelUser", new UserInfo());
        model.addAttribute("jspFile", "addUser");
        AtributesModel.addGlobalAtributeToModel(model, request);
        addLocalAtributesToModel(model);
        return "addUpdateUser"; 
    }
    
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String processCreateUser(@ModelAttribute("modelUser") @Valid UserInfo userInfoToBeAdd,
                                    BindingResult result, Model model, HttpServletRequest request,
                                    final RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            AtributesModel.addGlobalAtributeToModel(model, request);
            addLocalAtributesToModel(model);
            model.addAttribute("jspFile", "addUser");
            return "addUpdateUser";
        }
        String[] suppresedFields = result.getSuppressedFields();
        if(suppresedFields.length > 0){
            throw new RuntimeException("It has occurred an attempt bind the illegal fields: " 
                                + StringUtils.arrayToCommaDelimitedString(suppresedFields));
        }
        BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
        userInfoToBeAdd.setPassword(encrypt.encode(userInfoToBeAdd.getPassword()));
        logger.info("Save user... ["+userInfoToBeAdd+']');
        userInfoService.create(userInfoToBeAdd);
        if(Optional.ofNullable(request.getRemoteUser()).isPresent()){
            redirectAttributes.addFlashAttribute("createInfo", userInfoToBeAdd.getFirstName()+" "+userInfoToBeAdd.getLastName());
            redirectAttributes.addFlashAttribute("cssCreate", "success");
            return "redirect:/admin/users";
        }else{
            return "redirect:/login";
        }
    }
    
    @InitBinder(value = "modelUser")
    public void createProductBinder(WebDataBinder webDataBinder){
        webDataBinder.setAllowedFields("login", "password", "repeatPassword", "firstName", "lastName", "email", "status", "role");
        webDataBinder.setValidator(userInfoValidator); 
    }
    
    private Model addLocalAtributesToModel(Model model){
        model.addAttribute("roles", Arrays.asList(RoleUser.values()));
        model.addAttribute("statuses", Arrays.asList(UserStatus.values()));
        return model;
    }
}
