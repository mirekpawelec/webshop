/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.pawelec.webshop.model.Repository;
import pl.pawelec.webshop.service.RepositoryService;
import pl.pawelec.webshop.utils.AtributesModel;

/**
 *
 * @author mirek
 */
@Controller
@RequestMapping("/admin/repository")
public class RepositoryController {
    @Autowired
    private RepositoryService repositoryService;
    
    
    @RequestMapping
    public String getAllStock(Model model, HttpServletRequest request){
        model.addAttribute("wholeStock", repositoryService.getAll());
        model.addAttribute("jspFile", "warehouse");
        AtributesModel.addGlobalAtributeToModel(model, request);
        return "warehouse";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String getByCriteries(Model model, HttpServletRequest request){
        String sqlQuery = "from Repository ";
        String lastModificationDate = "", createDate = "";
        int sqlSymbol = 0;
        
        for(Map.Entry<String, String[]> params : request.getParameterMap().entrySet()){
            if(!params.getKey().contains("Date") && !params.getValue()[0].equals("")){
                if(sqlSymbol==0){
                    sqlQuery += "WHERE ";
                } else {
                    sqlQuery += " AND ";
                }
                sqlSymbol++;
                if(params.getValue()[0].contains("*")){
                    String newValue = params.getValue()[0].replace("*", "%");
                    sqlQuery += params.getKey().replace("PLUS", ".")+" LIKE '"+newValue+"'";
                } else if(params.getValue()[0].contains("%") || params.getValue()[0].contains("_")){
                    sqlQuery += params.getKey().replace("PLUS", ".")+" LIKE '"+params.getValue()[0]+"'";
                } else {
                    sqlQuery += params.getKey().replace("PLUS", ".")+" = '"+params.getValue()[0]+"'";
                } 
            } else if(params.getKey().contains("Date") && !params.getValue()[0].equals("")){
                if(params.getKey().equals("lastModificationDate")){
                    lastModificationDate = params.getValue()[0];
                } else {
                    createDate = params.getValue()[0];
                }
            }
            request.setAttribute(params.getKey(), params.getValue()[0]);
        }
        
        if(sqlSymbol==0 && (!lastModificationDate.isEmpty() || !createDate.isEmpty())){
            sqlQuery += "WHERE ";
        } else if(sqlSymbol>0 && (!lastModificationDate.isEmpty() || !createDate.isEmpty())){
            sqlQuery += " AND ";
        }
  
//        System.out.println("sqlSymbol="+sqlSymbol+ ", sql="+sqlQuery +", lastModificationDate="+lastModificationDate+", createDate="+createDate);      
        List<Repository> sqlResult = /*new ArrayList<>();*/ repositoryService.getByOwnCriteria(sqlQuery, lastModificationDate, createDate);
        if(sqlResult.size()>0){
            model.addAttribute("wholeStock", sqlResult);
        } else {
            model.addAttribute("cssNoDataFound", "info");
        }
        model.addAttribute("jspFile", "warehouse");
        AtributesModel.addGlobalAtributeToModel(model, request);
        return "warehouse";
    }
}
