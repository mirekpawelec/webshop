/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import org.junit.Before;
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




/**
 *
 * @author mirek
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/web-context.xml", "file:src/main/webapp/WEB-INF/spring/application-context.xml"})
@WebAppConfiguration
public class CartControllerTest {
    @Autowired 
    private WebApplicationContext webApplicationContext;
    @Autowired 
    MockHttpSession mockHttpSession;
    private MockMvc mockMvc;
    
    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }
    
    @Test
    public void test_get_cart_by_sessionId() throws Exception{
        //when & then
        this.mockMvc.perform( get("/cart/"+mockHttpSession.getId()) )
                .andExpect( model().attributeExists("sessionId"))
                .andExpect( model().attribute("sessionId", mockHttpSession.getId()) )
                .andExpect( model().attributeExists("jspFile"))
                .andExpect( model().attribute("jspFile", "cart") )
                .andExpect( model().attributeExists("lastRequestUrl") )
                .andExpect( view().name("cart") );
       
    }
}
