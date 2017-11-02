/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service.impl;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.pawelec.webshop.model.ClientMessage;
import pl.pawelec.webshop.service.ClientMessageService;
import pl.pawelec.webshop.service.ClientMessageService;

/**
 *
 * @author mirek
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/web-context.xml", "file:src/main/webapp/WEB-INF/spring/application-context.xml"})
@WebAppConfiguration
public class ClientMessageServiceImplTest {
    @Autowired
    private ClientMessageService clientMessageService;
    
    private static final String NAME = "Jan Kowalski";
    private static final String EMAIL = "example@example.com";
    private static final String SUBJECT = "Subject";
    private static final String CONTENT = "Some long message";
    private static final String CONTENT_2 = "New content message";
    
    @After
    public void clean(){
        ClientMessage deleteMssage = clientMessageService.getAll().stream()
                .filter(cm->cm.getName().equals(NAME) && cm.getEmail().equals(EMAIL) && cm.getSubject().equals(SUBJECT))
                .sorted((o1, o2) -> o1.getCreateDate().compareTo(o2.getCreateDate())).findFirst().orElse( new ClientMessage() );
        if(deleteMssage.getMessageId()!=null){
            clientMessageService.delete(deleteMssage); 
        }
    }
    
    @Test
    public void create_clientMessage_should_be_added(){
        //given
        ClientMessage patternMessage = new ClientMessage(NAME, EMAIL, SUBJECT, CONTENT);
        //when
        clientMessageService.create(patternMessage);
        ClientMessage newMssage = clientMessageService.getAll().stream()
                .filter(cm->cm.getName().equals(NAME) && cm.getEmail().equals(EMAIL) && cm.getSubject().equals(SUBJECT))
                .sorted((o1, o2) -> o1.getCreateDate().compareTo(o2.getCreateDate())).findFirst().get();
        //then
        assertEquals(patternMessage, newMssage);
    }
    
    @Test
    public void update_clientMessage_should_be_changed(){
        //given
        clientMessageService.create(new ClientMessage(NAME, EMAIL, SUBJECT, CONTENT));
        ClientMessage patternMssage = clientMessageService.getAll().stream()
                .filter(cm->cm.getName().equals(NAME) && cm.getEmail().equals(EMAIL) && cm.getSubject().equals(SUBJECT))
                .sorted((o1, o2) -> o1.getCreateDate().compareTo(o2.getCreateDate())).findFirst().get();
        //when
        patternMssage.setContent(CONTENT_2);
        clientMessageService.update(patternMssage);
        ClientMessage messageAfterUpdate = clientMessageService.getOneById(patternMssage.getMessageId());
        //then
        assertNotNull(messageAfterUpdate); 
        assertEquals(CONTENT_2, messageAfterUpdate.getContent());
    }
    
    @Test
    public void delete_clientMessage_should_be_removed(){
        //given
        ClientMessage patternMessage = new ClientMessage(NAME, EMAIL, SUBJECT, CONTENT);
        clientMessageService.create(patternMessage);
        //when
        ClientMessage deleteMssage = clientMessageService.getAll().stream()
                .filter(cm->cm.getName().equals(NAME) && cm.getEmail().equals(EMAIL) && cm.getSubject().equals(SUBJECT))
                .sorted((o1, o2) -> o1.getCreateDate().compareTo(o2.getCreateDate())).findFirst().get();
        clientMessageService.delete(deleteMssage);
        //then
        assertNull(clientMessageService.getOneById(deleteMssage.getMessageId()));
    } 
    
    @Test
    public void exists_and_count_method_positive_value_should_be_returned(){
        //given
        ClientMessage patternMessage = new ClientMessage(NAME, EMAIL, SUBJECT, CONTENT);
        //when
        clientMessageService.create(patternMessage);
        patternMessage = clientMessageService.getAll().stream()
                .filter(cm->cm.getName().equals(NAME) && cm.getEmail().equals(EMAIL) && cm.getSubject().equals(SUBJECT))
                .sorted((o1, o2) -> o1.getCreateDate().compareTo(o2.getCreateDate())).findFirst().get();
        //then
        assertTrue(clientMessageService.exists(patternMessage.getMessageId()));
        assertTrue(clientMessageService.count() >= 1);
    }
}
