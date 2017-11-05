/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.controller;

import org.junit.After;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.pawelec.webshop.model.UserInfo;
import pl.pawelec.webshop.service.UserInfoService;

/**
 *
 * @author mirek
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/web-context.xml", "file:src/main/webapp/WEB-INF/spring/application-context.xml"})
@WebAppConfiguration
public class LoginControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private UserInfoService userInfoService;
    MockHttpSession mockHttpSession;  
    private MockMvc mockMvc;
    
    private static final String LOGIN = "TestUser";
    private static final String PASSWORD = "Hasl@";
    private static final String REPEAT_PASSWORD = "Hasl@";
    private static final String FIRST_NAME = "Jan";
    private static final String LAST_NAME = "Kowalski";
    private static final String EMAIL = "example@example.com";
    private static final String ROLE = "ROLE_USER";

    
    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }
    
    @After
    public void clean(){
        UserInfo userInfo = userInfoService.getByLogin(LOGIN);
        if(userInfo!=null){
            userInfoService.delete( userInfo );
        }
    }
    
    @Test
    public void test_redirect_to_the_login_page_should_be_valid() throws Exception{
        //when & then
        this.mockMvc.perform( get("/login") )
                .andExpect( model().attributeExists("jspFile") )
                .andExpect( model().attribute("jspFile", "login") )
                .andExpect( model().attributeExists("role") )
                .andExpect( model().attributeExists("lastRequestUrl") )
                .andExpect( view().name("login") );;
    }
    
    @Test
    public void test_create_user_should_be_valid() throws Exception{
        //given
        UserInfo patternUser = new UserInfo();
        //when & then
        this.mockMvc.perform( get("/user/add") )
                .andExpect( model().attributeExists("jspFile") )
                .andExpect( model().attribute("jspFile", "newUser") )
                .andExpect( model().attributeExists("newUser") )
                .andExpect( model().attribute("newUser", patternUser) )
                .andExpect( model().attributeExists("role") )
                .andExpect( model().attributeExists("lastRequestUrl") )
                .andExpect( view().name("addUser") );
    }
    
    @Test
    public void test_process_creation_a_user_should_not_get_any_errors() throws Exception{
        //given
        UserInfo userAdded = new UserInfo();
        UserInfo patternUser = new UserInfo(LOGIN, PASSWORD, REPEAT_PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, ROLE);
        //when
        this.mockMvc.perform( post("/user/add").flashAttr("newUser", patternUser) )
                .andExpect( redirectedUrl("/login") );
        userAdded = userInfoService.getByLogin(LOGIN);
        //then
        assertNotNull(userInfoService.getByLogin(LOGIN));
        assertEquals(LOGIN, userAdded.getLogin());
        assertEquals(FIRST_NAME, userAdded.getFirstName());
        assertEquals(LAST_NAME, userAdded.getLastName());
    }
    
}
