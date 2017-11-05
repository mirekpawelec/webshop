/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.controller;


import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
@Controller
@RequestMapping("/admin/users")
public class UserInfoController {
    Logger logger = Logger.getLogger(UserInfoController.class);
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserInfoValidator userInfoValidator;
    
    
    
    @RequestMapping
    public String getAllUser(Model model, HttpServletRequest request){
        model.addAttribute("users", userInfoService.getAll());
        model.addAttribute("jspFile", "users");
        AtributesModel.addGlobalAtributeToModel(model, request);
        return "users";
    }
    
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String updateUser(@PathVariable("id") String userId, Model model, HttpServletRequest request){
        UserInfo updateUser = userInfoService.getOneById(Long.valueOf(userId));        
        updateUser.setPassword("");
        updateUser.setRepeatPassword("");
        model.addAttribute("modelUser", updateUser);
        model.addAttribute("jspFile", "updateUser");
        AtributesModel.addGlobalAtributeToModel(model, request);
        addLocalAtributesToModel(model);
        return "addUpdateUser"; 
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateUser(@ModelAttribute("modelUser") @Valid UserInfo userInfoToBeUpdate
                                    , BindingResult result, Model model, HttpServletRequest request
                                    , final RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            model.addAttribute("jspFile", "updateUser");
            AtributesModel.addGlobalAtributeToModel(model, request);
            addLocalAtributesToModel(model);
            return "addUpdateUser";
        }
        String[] suppresedFields = result.getSuppressedFields();
        if(suppresedFields.length > 0){
            throw new RuntimeException("It has occurred an attempt bind the illegal fields: " 
                                + StringUtils.arrayToCommaDelimitedString(suppresedFields));
        }
        if(!userInfoToBeUpdate.getPassword().isEmpty()){
            BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
            userInfoToBeUpdate.setPassword(encrypt.encode(userInfoToBeUpdate.getPassword()));
        } else {
            userInfoToBeUpdate.setPassword(userInfoService.getOneById(userInfoToBeUpdate.getUserId()).getPassword());
        }
        logger.info("Save user... ["+userInfoToBeUpdate+']'); 
        userInfoService.update(userInfoToBeUpdate);
        redirectAttributes.addFlashAttribute("updateInfo", userInfoToBeUpdate.getFirstName()+" "+userInfoToBeUpdate.getLastName());
        redirectAttributes.addFlashAttribute("cssUpdate", "success");
        return "redirect:/admin/users"; 
    }
    
    @InitBinder(value = "modelUser")
    public void updateProductBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("lastModificationDate");
        webDataBinder.setValidator(userInfoValidator); 
    }
    
    private Model addLocalAtributesToModel(Model model){
        model.addAttribute("roles", Arrays.asList(RoleUser.values()));
        model.addAttribute("statuses", Arrays.asList(UserStatus.values()));
        return model;
    }
    
}
