/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.controller;

import java.math.BigDecimal;
import org.apache.log4j.Logger;
import org.junit.After;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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
import pl.pawelec.webshop.converter.CartNotFoundException;
import pl.pawelec.webshop.model.Cart;
import pl.pawelec.webshop.model.Product;
import pl.pawelec.webshop.model.enum_.CartStatus;
import pl.pawelec.webshop.model.enum_.ProductStatus;
import pl.pawelec.webshop.service.CartService;
import pl.pawelec.webshop.service.ProductService;

/**
 *
 * @author mirek
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/web-context.xml", "file:src/main/webapp/WEB-INF/spring/application-context.xml"})
@WebAppConfiguration
public class CartRestControllerTest {
    Logger logger = Logger.getLogger(CartRestControllerTest.class);
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @Autowired
    MockHttpSession mockHttpSession;
    private MockMvc mockMvc;
    
    private static final String PRODUCT_NO = "000.000.01";
    private static final String NAME = "Smartphone";
    private static final String MANUFACTURER = "SomeManufacturer";
    private static final String CATEGORY = "Smartphone";
    private static final String DESCRIPTION = "Some description";
    private static final BigDecimal UNIT_PRICE = new BigDecimal("999.99");
    private static final int QUANTITY = 1;
    
    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        Product testProduct = new Product.Builder()
                .withProductNo(PRODUCT_NO)
                .withName(NAME)
                .withManufacturer(MANUFACTURER)
                .withCategory(CATEGORY)
                .withDescription(DESCRIPTION)
                .withUnitPrice(UNIT_PRICE)
                .withQuantityInBox(QUANTITY)
                .withStatus(ProductStatus.ED.name())
                .build();
        productService.create(testProduct);
    }
    
    @After
    public void clean(){
        try{
            cartService.delete( cartService.getBySessionId(mockHttpSession.getId()).stream().filter(cart->cart.getStatus().equals(CartStatus.RE.name())).findFirst().get() );
        } catch(CartNotFoundException cfe){
            logger.info("No cart found: " + cfe.getSessionId());
        }
        productService.delete( productService.getOneByProductNo(PRODUCT_NO) );
    }
    
    @Test
    public void create_method_a_new_cart_Json_object_should_be_added_and_returned() throws Exception{
        //given
        Cart patternCart = new Cart(mockHttpSession.getId());
        //when & then
        this.mockMvc.perform( post("/rest/cart/"+mockHttpSession.getId()) )  
                .andExpect( status().is(200) )
                .andExpect( jsonPath("cartId").exists() )
                .andExpect( jsonPath("$.cartId").isNotEmpty() )
                .andExpect( jsonPath("sessionId").exists() )
                .andExpect( jsonPath("$.sessionId").value(mockHttpSession.getId()) )
                .andExpect( jsonPath("status").exists() )
                .andExpect( jsonPath("$.status").value(CartStatus.RE.name()) )
                .andExpect( jsonPath("costOfAllItems").exists() )
                .andExpect( jsonPath("$.costOfAllItems").value(0) )
                .andExpect( jsonPath("cartItemSet").exists() )
                .andExpect( jsonPath("$.cartItemSet").isEmpty() );   
        assertEquals(patternCart, cartService.getBySessionId(mockHttpSession.getId()).get(0));
    }
    
    @Test
    public void read_method_a_cart_Json_object_should_be_returned() throws Exception{
        //given
        this.mockMvc.perform( post("/rest/cart/"+mockHttpSession.getId()) )
                .andExpect( status().is(200) );
        //when & then
        this.mockMvc.perform( get("/rest/cart/"+mockHttpSession.getId()) )  
                .andExpect( status().is(200) )
                .andExpect( jsonPath("cartId").exists() )
                .andExpect( jsonPath("$.cartId").isNotEmpty() )
                .andExpect( jsonPath("sessionId").exists() )
                .andExpect( jsonPath("$.sessionId").value(mockHttpSession.getId()) )
                .andExpect( jsonPath("status").exists() )
                .andExpect( jsonPath("$.status").value(CartStatus.RE.name()) )
                .andExpect( jsonPath("costOfAllItems").exists() )
                .andExpect( jsonPath("$.costOfAllItems").value(0) )
                .andExpect( jsonPath("cartItemSet").exists() )
                .andExpect( jsonPath("$.cartItemSet").isEmpty() ); 
    }
    
    @Test(expected = CartNotFoundException.class)
    public void delete_method_a_given_cart_should_be_removed() throws Exception{
        //given
        this.mockMvc.perform( post("/rest/cart/"+mockHttpSession.getId()) )
                .andExpect( status().is(200) );
        Cart deleteCart = cartService.getBySessionId(mockHttpSession.getId()).get(0);
        //when & then
        this.mockMvc.perform( delete("/rest/cart/"+deleteCart.getCartId()) )
                .andExpect( status().is(204));
        assertNull(cartService.getBySessionId(mockHttpSession.getId()).get(0));
        
    }
    
    @Test
    public void add_cartItem_to_cart_should_be_added() throws Exception{
        //given
        Product addingProduct = productService.getOneByProductNo(PRODUCT_NO);
        //when & then
        this.mockMvc.perform( put("/rest/cart/add/"+addingProduct.getProductId()).session(mockHttpSession) )
                .andExpect( status().is(204) );
        Cart cart = cartService.getBySessionId(mockHttpSession.getId()).get(0);
        assertEquals(1, cart.getCartItemSet().size());
        assertEquals(addingProduct, cart.getCartItemSet().iterator().next().getProduct());
    }
    
    @Test
    public void delete_cartItem_from_cart_should_be_removed() throws Exception{
        //given
        Product addingProduct = productService.getOneByProductNo(PRODUCT_NO);
        this.mockMvc.perform( put("/rest/cart/add/"+addingProduct.getProductId()).session(mockHttpSession) )
                .andExpect( status().is(204) );
        //when & then
        this.mockMvc.perform( put("/rest/cart/delete/"+addingProduct.getProductId()).session(mockHttpSession) )
                .andExpect( status().is(204) );
        Cart cart = cartService.getBySessionId(mockHttpSession.getId()).get(0);
        assertEquals(0, cart.getCartItemSet().size());
    }

}
