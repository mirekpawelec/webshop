/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import pl.pawelec.webshop.model.Repository;
import pl.pawelec.webshop.model.Storageplace;
import pl.pawelec.webshop.service.ProductService;
import pl.pawelec.webshop.service.RepositoryService;
import pl.pawelec.webshop.service.StorageareaService;
import pl.pawelec.webshop.service.StorageplaceService;

/**
 *
 * @author mirek
 */
public class tests {
    
    public static void main(String[] args) {
        
//        Product product = new Product.Builder().withProductNo("123.456.78").build();
//        System.out.println(product);
//        
//        ResourceBundleMessageSource rbm = new ResourceBundleMessageSource();
//        rbm.setBasename("messages");
//        
//        LocalValidatorFactoryBean lvfb = new LocalValidatorFactoryBean();
//        lvfb.setValidationMessageSource(rbm);
//
//        DriverManagerDataSource dmds = new DriverManagerDataSource();
//        
//        JpaTransactionManager jtm = new JpaTransactionManager();
        
        ApplicationContext context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/spring/applicationContext.xml");
        //ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        //TestBean test = context.getBean(TestBean.class);
        //System.out.println(test);
        ProductService ps = context.getBean(ProductService.class);
        StorageareaService sas = context.getBean(StorageareaService.class);
        RepositoryService rs = context.getBean(RepositoryService.class);
        StorageplaceService sps = context.getBean(StorageplaceService.class);
//      
//        Product product = new Product.Builder()
//                .withProductNo("123.321.10")
//                .withName("Note 8")
//                .withManufacturer("Samsung")
//                .withCategory("Smartfon")
//                .withDescription("Jakiś telefon, taki całkiem całkiem")
//                .withUnitPrice(new BigDecimal(100))
//                .withQuantityInBox(1)
//                .withStatus("OK")
//                .build();
//        Product product2 = new Product.Builder()
//                .withProductId(9l)
//                .withProductNo("123.321.11")
//                .withName("Edge 8")
//                .withManufacturer("Samsung")
//                .withCategory("Smartfon")
//                .withDescription("Jakiś telefon, chyba nowy")
//                .withUnitPrice(new BigDecimal(200))
//                .withQuantityInBox(1)
//                .withStatus("OK")
//                .build();
//        Product product3 = new Product.Builder()
//                //.withProductId(33l)
//                .withProductNo("123.321.99")
//                .withName("Galaxy S8")
//                .withManufacturer("Samsung")
//                .withCategory("Smartfon")
//                .withDescription("Chyba dobry ;)")
//                .withUnitPrice(new BigDecimal(300))
//                .withQuantityInBox(1)
//                .withStatus("OK")
//                .build();

//        ps.create(product3);
//        ps.create(product2);
//        ps.create(product3);
        
//        System.out.println( ps.getOneById(1L) );
//        ps.getAll().stream().filter((t) -> t.getProductNo().contains("456")).forEach(System.out::println);
//        ps.update(product3);
//        //ps.delete(product2);
//        System.out.println( ps.count() );
//        System.out.println( ps.exists(3l) );
//        //System.out.println( ps.getOneById(4l) );
//        ps.deleteById(33l);
//        System.out.println( ps.exists(3l) );
//        ps.deleteAll();
//        ps.getAll().parallelStream().forEach(s->System.out.println(s));
//        
//        LocalDateTime dt = LocalDateTime.of(2017, 11, 10, 15, 30, 45);
//        Timestamp time = Timestamp.valueOf(dt);
//        LocalDateTimeConverter ldtc = new LocalDateTimeConverter();
//        System.out.println("ts -> ldt= " + ldtc.convertToEntityAttribute(time) );
//        System.out.println("ldt -> ts= " + ldtc.convertToDatabaseColumn(dt) );
//        
//        System.out.println( ps.getOneByProductNo("123.123.13") );

//        Logger log = Logger.getLogger(tests.class);
//        log.setLevel(Level.DEBUG);
//        log.trace("Trace Message!");
//        log.debug("Debug Message!");
//        log.info("Info Message!");
//        log.warn("Warn Message!");
//        log.error("Error Message!", new Exception("New exception!"));
//        log.fatal("Fatal Message!");
//        if(log.isDebugEnabled()){
//          log.debug("to działa?");
//        }

//        ps.getAllManufacturers().forEach(System.out::println);
//        ps.getAllCategories().forEach(System.out::println);





//          System.out.println( sas.count() );
//          System.out.println( sas.exists(2l) );      
          //Storagearea storagearea = new Storagearea();
          //storagearea.setAreaId(3l);
          //storagearea.setName("M3");
          //storagearea.setDescription("Magazyn nr 3");
          //sas.create(storagearea);
          //System.out.println(sas.getById(2l));
          //sas.update(storagearea);
//          try{
          //sas.deleteById( 1l );
//          } catch (DataIntegrityViolationException e){
//              System.out.println( e.getMessage() );
//          }
          //System.out.println( sas.getByDescription("Magazyn") );
          //System.out.println(sas.getAll());
          
          
          
          
        
        //System.out.println( rs.count() );
        //System.out.println( rs.exists(14l) );
        //System.out.println( rs.getByLoadunitNo("1010100003") );
        //System.out.println( rs.getById(13l) );    
//        Repository r = new Repository();
//        r.setLoadunitId(16l);
//        r.setLoadunitNo( "1010100004" );
//        r.setProductId( ps.getOneById( 35l ) );
//        r.setQuantity(1);
//        r.setPlaceId( sps.getById( 7l ) );
//        r.setConditions("USED");
//        r.setQualityStatus(Short.valueOf("0"));
//        r.setStatus("FI");
//        System.out.println(r);  
        //rs.create(r);
        //rs.update(r);
        //rs.deleteById(16l);
        //System.out.println( rs.getById(16l) );
        
        
        
        
        
//        System.out.println( sps.count() );
//        System.out.println( sps.exists(5l) );
//        System.out.println( sps.getByPlaceNo("G03") );
        
        
    }
}
