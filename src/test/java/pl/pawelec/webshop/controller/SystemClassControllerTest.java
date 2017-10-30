/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.controller;

import org.apache.log4j.Logger;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.pawelec.webshop.exception.NoSystemClassKeyFoundException;
import pl.pawelec.webshop.model.SystemClass;
import pl.pawelec.webshop.service.SystemClassService;

/**
 *
 * @author mirek
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/web-context.xml", "file:src/main/webapp/WEB-INF/spring/application-context.xml"})
@WebAppConfiguration
public class SystemClassControllerTest {
    Logger logger = Logger.getLogger(SystemClassControllerTest.class);
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private SystemClassService systemClassService;
    private MockMvc mockMvc;
    
    public static final String SYMBOL_ONE = "first_class_test";
    public static final String SYMBOL_TWO = "second_class_test";
    public static final String NAME = "test";
    public static final String VALUE = "0";
    public static final String DESCRIPTION = "some system class";
    public static final String NEW_VALUE = "1";
    public static final String NEW_DESCRIPTION = "new description";

    
    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        systemClassService.create( new SystemClass(SYMBOL_ONE, NAME, VALUE, DESCRIPTION) ); 
    }
    
    @After
    public void clean(){
        try{
            systemClassService.delete( systemClassService.getByUniqueKey(SYMBOL_ONE, NAME) );
        } catch(NoSystemClassKeyFoundException nsce){
            logger.info("No system class found: "+ nsce.getSymbol() +" - "+ nsce.getName());
        }
        try{
            systemClassService.delete( systemClassService.getByUniqueKey(SYMBOL_TWO, NAME) );
        } catch(NoSystemClassKeyFoundException nsce){
            logger.info("No system class found: "+ nsce.getSymbol() +" - "+ nsce.getName());
        }
    }
    
    @Test
    public void test_get_all_class() throws Exception{
        //given
        SystemClass patternClass = systemClassService.getByUniqueKey(SYMBOL_ONE, NAME);
        //when & then
        this.mockMvc.perform( get("/admin/classes") )
                .andExpect( model().attributeExists("systemClasses"))
                .andExpect( model().attribute("systemClasses", hasItem(patternClass)))
                .andExpect( model().attributeExists("jspFile"))
                .andExpect( model().attribute("jspFile", "systemClasses"))
                .andExpect( model().attributeExists("lastRequestUrl") )
                .andExpect( view().name("systemClasses") );
    }
    
    @Test
    public void test_add_classes_a_empty_object_should_be_added() throws Exception{
        //given
        SystemClass patternClass = new SystemClass();
        //when & then
        this.mockMvc.perform( get("/admin/classes/add") )
                .andExpect( model().attributeExists("newSystemClass"))
                .andExpect( model().attribute("newSystemClass", patternClass))
                .andExpect( model().attributeExists("jspFile"))
                .andExpect( model().attribute("jspFile", "addSystemClass"))
                .andExpect( model().attributeExists("lastRequestUrl") )
                .andExpect( view().name("addSystemClass") );
    }
    
    @Test
    public void test_a_process_of_adding_a_class() throws Exception{
        //given
        SystemClass patternClass = new SystemClass(SYMBOL_TWO, NAME, VALUE, DESCRIPTION);
        //when & then
        this.mockMvc.perform( post("/admin/classes/add").flashAttr("newSystemClass", patternClass) )
                .andExpect( redirectedUrl("/admin/classes") );
        SystemClass addedClass = systemClassService.getByUniqueKey(SYMBOL_TWO, NAME);
        assertNotNull(addedClass);
        assertEquals(SYMBOL_TWO, addedClass.getSymbol());
        assertEquals(NAME, addedClass.getName());
    }
    
    @Test
    public void test_a_process_of_modify_a_class() throws Exception{
        //given
        SystemClass patternClass = systemClassService.getByUniqueKey(SYMBOL_ONE, NAME);
        patternClass.setValue(NEW_VALUE);
        patternClass.setDescription(NEW_DESCRIPTION);
        //when & then
        this.mockMvc.perform( post("/admin/classes/update").flashAttr("updateSystemClass", patternClass) )
                .andExpect( redirectedUrl("/admin/classes") );
        SystemClass modifiedClass = systemClassService.getByUniqueKey(SYMBOL_ONE, NAME);
        assertNotNull(modifiedClass);
        assertEquals(SYMBOL_ONE, modifiedClass.getSymbol());
        assertEquals(NAME, modifiedClass.getName());
        assertEquals(NEW_VALUE, modifiedClass.getValue());
        assertEquals(NEW_DESCRIPTION, modifiedClass.getDescription());
    }
    
    @Test(expected = NoSystemClassKeyFoundException.class)
    public void test_delete_a_class_should_be_removed_and_thrown_out_exception() throws Exception{
        //given
        SystemClass patternClass = systemClassService.getByUniqueKey(SYMBOL_ONE, NAME);
        //when & then
        this.mockMvc.perform( post("/admin/classes/"+patternClass.getClassId()+"/delete") )
                .andExpect( redirectedUrl("/admin/classes") );
        SystemClass removedClass = systemClassService.getByUniqueKey(SYMBOL_ONE, NAME);
        assertNull(removedClass); 
    }
}
