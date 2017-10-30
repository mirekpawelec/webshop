/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.validator;

import java.math.BigDecimal;
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
import pl.pawelec.webshop.model.Product;
import pl.pawelec.webshop.service.ProductService;

/**
 *
 * @author mirek
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/web-context.xml", "file:src/main/webapp/WEB-INF/spring/application-context.xml"})
@WebAppConfiguration
public class ProductValidatorTest {
    @Autowired
    private ProductValidator productValidator;
    @Autowired
    private ProductService productService;
    
    private static final String PRODUCT_NO_1 = "000.000.00";
    private static final String NAME = "Tablet";
    private static final String MANUFACTURER = "SomeManufacturer";
    private static final String CATEGORY = "Tablet";
    private static final String DESCRIPTION = "Some description";
    private static final BigDecimal UNIT_PRICE = new BigDecimal("99.99");
    private static final int QUANTITY = 1;
    
    private static final String PRODUCT_NO_2 = "999.999.99";
    
    @Before
    public void setup(){
        Product testProduct = new Product.Builder()
                .withProductNo(PRODUCT_NO_1)
                .withName(NAME)
                .withManufacturer(MANUFACTURER)
                .withCategory(CATEGORY)
                .withDescription(DESCRIPTION)
                .withUnitPrice(UNIT_PRICE)
                .withQuantityInBox(QUANTITY)
                .build();
        productService.create(testProduct);
    }
    
    @After
    public void clean(){
        productService.delete( productService.getOneByProductNo(PRODUCT_NO_1) );
    }
    
    @Test
    public void a_product_with_existing_productNo_should_be_invalid(){
        // given
        Product patternProduct = new Product.Builder()
                .withProductNo(PRODUCT_NO_1)
                .withName(NAME)
                .withManufacturer(MANUFACTURER)
                .withCategory(CATEGORY)
                .withDescription(DESCRIPTION)
                .withUnitPrice(UNIT_PRICE)
                .withQuantityInBox(QUANTITY)
                .build();
        BindException bindException = new BindException(patternProduct, "existingProduct");
        // when
        ValidationUtils.invokeValidator(productValidator, patternProduct, bindException);
        // then
        assertEquals("productNo", bindException.getFieldError().getField());
        assertEquals(1, bindException.getErrorCount());
    }
    
    @Test
    public void a_empty_product_should_throw_7_validation_errors(){
        // given
        Product patternProduct = new Product();
        BindException bindException = new BindException(patternProduct, "emptyProduct");
        //when
        ValidationUtils.invokeValidator(productValidator, patternProduct, bindException);
        //then
        assertEquals(7, bindException.getErrorCount());
        assertTrue(bindException.getFieldError("unitPrice").getDefaultMessage().contains("Określ cenę"));
        assertTrue(bindException.getFieldError("quantityInBox").getDefaultMessage().contains("Wprowadź ilość sztuk w opakowaniu"));
        assertTrue(bindException.getFieldError("category").getDefaultMessage().contains("Określ kategorie"));
        assertTrue(bindException.getFieldError("productNo").getDefaultMessage().contains("Numer produktu jest wymagany"));
        assertTrue(bindException.getFieldError("manufacturer").getDefaultMessage().contains("Wprowadź producenta"));
        assertTrue(bindException.getFieldError("description").getDefaultMessage().contains("Dodaj opis"));
        assertTrue(bindException.getFieldError("name").getDefaultMessage().contains("Wprowadź nazwę produktu"));
    }
    
    @Test
    public void a_valid_product_should_not_get_any_error_during_validation(){
        //given
        Product patternProduct = new Product.Builder()
                .withProductNo(PRODUCT_NO_2)
                .withName(NAME)
                .withManufacturer(MANUFACTURER)
                .withCategory(CATEGORY)
                .withDescription(DESCRIPTION)
                .withUnitPrice(UNIT_PRICE)
                .withQuantityInBox(QUANTITY)
                .build();
        BindException bindException = new BindException(patternProduct, "correctProduct");
        //when
        ValidationUtils.invokeValidator(productValidator, patternProduct, bindException);
        //then
        assertEquals(0, bindException.getErrorCount());
    }
}
