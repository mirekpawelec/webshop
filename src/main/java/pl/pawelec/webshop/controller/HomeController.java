/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author mirek
 */
@RequestMapping("/home")
@Controller
public class HomeController {
    
    @RequestMapping
    public String welcome(Model model){
        model.addAttribute("jspFile", "welcome");
        return "welcome";
    }
}
