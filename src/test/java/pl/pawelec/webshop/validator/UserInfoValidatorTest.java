/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.validator;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;
import pl.pawelec.webshop.model.UserInfo;
import pl.pawelec.webshop.service.UserInfoService;

/**
 *
 * @author mirek
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/web-context.xml", "file:src/main/webapp/WEB-INF/spring/application-context.xml"})
@WebAppConfiguration
public class UserInfoValidatorTest {
    @Autowired
    private UserInfoValidator userInfoValidator;
    @Autowired
    private UserInfoService userInfoService;
    
    private static final String LOGIN = "TestUser";
    private static final String PASSWORD = "Hasl@";
    private static final String REPEAT_PASSWORD = "Hasl@";
    private static final String FIRST_NAME = "Jan";
    private static final String LAST_NAME = "Kowalski";
    private static final String EMAIL = "example@example.com";
    private static final String ROLE = "ROLE_TEST";
    
    private static final String DIFFERENT_PASSWORD_NO_1 = "First!";
    private static final String DIFFERENT_PASSWORD_NO_2 = "Second!";
    
    private static final String LOGIN_OF_CORRECTLY_USER = "TestUser2";
    private static final String PASSWORD_OF_CORRECTLY_USER = "Hasl@2";
    private static final String REPEAT_PASSWORD_OF_CORRECTLY_USER = "Hasl@2";
    private static final String FIRST_NAME_OF_CORRECTLY_USER = "Tomek";
    private static final String LAST_NAME_OF_CORRECTLY_USER = "Nowak";
    private static final String EMAIL_OF_CORRECTLY_USER = "example@example.com";
    private static final String ROLE_OF_CORRECTLY_USER = "ROLE_TEST";
    
    @Before
    public void setup(){
        UserInfo testUser = new UserInfo(LOGIN, PASSWORD, REPEAT_PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, ROLE);
        userInfoService.create(testUser);
    }
    
    @After
    public void clean(){
        userInfoService.delete(userInfoService.getByLogin(LOGIN));
    }
    
    @Test
    public void a_user_with_existing_login_should_be_invalid(){
        //given
        UserInfo patternUser = new UserInfo(LOGIN, PASSWORD, REPEAT_PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, ROLE);
        BindException bindException = new BindException(patternUser, "userLogin");
        //when
        ValidationUtils.invokeValidator(userInfoValidator, patternUser, bindException);
        //then
        assertEquals(1, bindException.getErrorCount());
        assertEquals("login", bindException.getFieldError().getField());
        assertTrue(bindException.getFieldError("login").getDefaultMessage().contains("Podany login jest już używany"));
    }
    
    @Test
    public void passwords_are_not_the_same_should_be_invalid(){
        //given
        UserInfo patternUser = new UserInfo(LOGIN_OF_CORRECTLY_USER, DIFFERENT_PASSWORD_NO_1, DIFFERENT_PASSWORD_NO_2
                , FIRST_NAME_OF_CORRECTLY_USER, LAST_NAME_OF_CORRECTLY_USER, EMAIL_OF_CORRECTLY_USER, ROLE_OF_CORRECTLY_USER);
        BindException bindException = new BindException(patternUser, "differentUserPasswords");
        //when
        ValidationUtils.invokeValidator(userInfoValidator, patternUser, bindException);
        //then        
        assertEquals(1, bindException.getErrorCount());
        assertEquals("repeatPassword", bindException.getFieldError().getField());
        assertTrue(bindException.getFieldError("repeatPassword").getCodes()[0].contains("pl.pawelec.webshop.validator.UserPsswordValidator.message"));
    }
    
    @Test
    public void a_empty_user_should_throw_7_validation_errors(){
        //given
        UserInfo patternUser = new UserInfo();
        BindException bindException = new BindException(patternUser, "emptyUser");
        //when
        ValidationUtils.invokeValidator(userInfoValidator, patternUser, bindException);
        //then
        assertEquals(7, bindException.getErrorCount());
        assertTrue(bindException.getFieldError("password").getDefaultMessage().contains("Hasło jest wymagane"));
        assertTrue(bindException.getFieldError("email").getDefaultMessage().contains("Adres email jest wymagany"));
        assertTrue(bindException.getFieldError("repeatPassword").getDefaultMessage().contains("Hasło jest wymagane"));
        assertTrue(bindException.getFieldError("role").getDefaultMessage().contains("Wybierz zakres uprawnień"));
        assertTrue(bindException.getFieldError("firstName").getDefaultMessage().contains("Imię jest wymagane"));
        assertTrue(bindException.getFieldError("lastName").getDefaultMessage().contains("Nazwisko jest wymagane"));
        assertTrue(bindException.getFieldError("login").getDefaultMessage().contains("Login jest wymagany"));
    }
    
    @Test
    public void a_valid_user_should_not_be_any_errors(){
        //given
        UserInfo patternUser = new UserInfo(LOGIN_OF_CORRECTLY_USER, PASSWORD_OF_CORRECTLY_USER, REPEAT_PASSWORD_OF_CORRECTLY_USER
                , FIRST_NAME_OF_CORRECTLY_USER, LAST_NAME_OF_CORRECTLY_USER, EMAIL_OF_CORRECTLY_USER, ROLE_OF_CORRECTLY_USER);
        BindException bindException = new BindException(patternUser, "validUser");
        //when
        ValidationUtils.invokeValidator(userInfoValidator, patternUser, bindException);
        //then
        assertEquals(0, bindException.getErrorCount());
    }
}
