/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.validator;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;
import pl.pawelec.webshop.model.SystemClass;
import pl.pawelec.webshop.service.SystemClassService;

/**
 *
 * @author mirek
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/web-context.xml", "file:src/main/webapp/WEB-INF/spring/application-context.xml"})
@WebAppConfiguration
public class SystemClassValidatorTest {
    @Autowired
    private SystemClassValidator systemClassValidator;
    @Autowired
    private SystemClassService systemClassService;
    
    public static final String SYMBOL = "test_test";
    public static final String NAME = "test";
    public static final String VALUE = "0";
    public static final String DESCRIPTION = "some test system class";
    
    public static final String SYMBOL_OF_CORRECTLY_CLASS = "correctly_class";
    public static final String NAME_OF_CORRECTLY_CLASS = "class";
    public static final String VALUE_OF_CORRECTLY_CLASS = "0";
    public static final String DESCRIPTION_OF_CORRECTLY_CLASS = "some test system class";
    
    @Before
    public void setup(){
        SystemClass testSystemClass = new SystemClass(SYMBOL, NAME, VALUE, DESCRIPTION);
        systemClassService.create(testSystemClass);
    }
    
    @After
    public void clean(){
        systemClassService.delete(systemClassService.getByUniqueKey(SYMBOL, NAME));
    }
    
    @Test
    public void a_system_class_with_existing_key_should_be_invalid(){
        //given
        SystemClass patternSystemClass = new SystemClass(SYMBOL, NAME, VALUE, DESCRIPTION);
        BindException bindException = new BindException(patternSystemClass, "existingSystemClass");
        //when
        ValidationUtils.invokeValidator(systemClassValidator, patternSystemClass, bindException);
        //then
        assertEquals(1, bindException.getErrorCount());
        assertEquals("symbol", bindException.getFieldError().getField());
        assertTrue(bindException.getFieldError("symbol").getCodes()[0].contains("pl.pawelec.webshop.validator.SystemClassUniqueKeyValidator.message"));
    }
    
    @Test
    public void a_empty_system_class_should_throw_3_validation_errors(){
        //given
        SystemClass patternSystemClass = new SystemClass();
        BindException bindException = new BindException(patternSystemClass, "emptySystemClass");
        //when
        ValidationUtils.invokeValidator(systemClassValidator, patternSystemClass, bindException);
        //then
        assertEquals(3, bindException.getErrorCount());
        assertTrue(bindException.getFieldError("value").getDefaultMessage().contains("Wartość jest wymagana"));
        assertTrue(bindException.getFieldError("symbol").getDefaultMessage().contains("Symbol jest wymagany")); 
        assertTrue(bindException.getFieldError("name").getDefaultMessage().contains("Nazwa jest wymagana")); 
    }
    
    @Test
    public void a_valid_system_class_should_not_be_invalid(){
        //given
        SystemClass patternSystemClass = new SystemClass(SYMBOL_OF_CORRECTLY_CLASS, 
                NAME_OF_CORRECTLY_CLASS, VALUE_OF_CORRECTLY_CLASS, DESCRIPTION_OF_CORRECTLY_CLASS);
        BindException bindException = new BindException(patternSystemClass, "correctlySystemClass");
        //when
        ValidationUtils.invokeValidator(systemClassValidator, patternSystemClass, bindException);
        //then
        assertEquals(0, bindException.getErrorCount());
    }
}
