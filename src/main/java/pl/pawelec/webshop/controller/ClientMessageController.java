/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.pawelec.webshop.model.ClientMessage;
import pl.pawelec.webshop.model.enum_.MessageStatus;
import pl.pawelec.webshop.service.ClientMessageService;
import pl.pawelec.webshop.utils.AtributesModel;

/**
 *
 * @author mirek
 */
@RequestMapping("/admin/messages")
@Controller
public class ClientMessageController {
    Logger logger = Logger.getLogger(ClientMessageController.class);
    @Autowired
    private ClientMessageService clientMessageService;
    
    @RequestMapping
    public String getAllMessages(Model model, HttpServletRequest request){
        model.addAttribute("messages", clientMessageService.getAll());
        model.addAttribute("jspFile", "clientMessage");
        AtributesModel.addGlobalAtributeToModel(model, request);
        return "clientMessages";
    }
    
    @RequestMapping(value = "/{id}/delete")
    public String deleteMessage(@PathVariable("id") String messageId, final RedirectAttributes redirectAttributes){
        ClientMessage deleteMessage = clientMessageService.getOneById(Long.valueOf(messageId));
        clientMessageService.delete(deleteMessage);
        redirectAttributes.addFlashAttribute("deletedMessage", "danger");
        redirectAttributes.addFlashAttribute("infoDeletedMessage", deleteMessage.getName()+" ("+deleteMessage.getEmail()+")");
        return "redirect:/admin/messages";
    }
    
    @RequestMapping(value = "/{id}/{status}/modify")
    public String changeStatus(@PathVariable("id") String messageId, @PathVariable("status") String status, 
                               final RedirectAttributes redirectAttributes){
        ClientMessage modifyMessage = clientMessageService.getOneById(Long.valueOf(messageId));
        modifyMessage.setStatus(MessageStatus.valueOf(status).getName());
        clientMessageService.update(modifyMessage);
        return "redirect:/admin/messages";
    }
}
