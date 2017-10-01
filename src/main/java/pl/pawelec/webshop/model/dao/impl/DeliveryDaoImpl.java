/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pl.pawelec.webshop.model.Delivery;
import pl.pawelec.webshop.model.enum_.DeliveryStatus;
import pl.pawelec.webshop.model.Storageplace;
import pl.pawelec.webshop.model.dao.AbstrDao;
import pl.pawelec.webshop.model.dao.DeliveryDao;

/**
 *
 * @author mirek
 */
@Repository
public class DeliveryDaoImpl extends AbstrDao<Delivery> implements DeliveryDao{

    private static String DEFAULT_PLACE_NO = "select place";
    private static String DEFAULT_PLACE_NAME = "default place";
    private List<Delivery> deliverysList;
    
    public List<Delivery> getByDriver(String firstName, String lastName, String phoneNo) {
        return deliverysList = this.getAll().parallelStream()
                .filter((delivery) -> { if(firstName!=null && !firstName.isEmpty()){ 
                                            return delivery.getDriverFirstName().equals(firstName);
                                        } else {
                                            return true;
                                        }
                })
                .filter((delivery) -> { if(lastName!=null && !lastName.isEmpty()){ 
                                            return delivery.getDriverLastName().equals(lastName);
                                        } else {
                                            return true;
                                        }   
                })
                .filter((delivery) -> { if(phoneNo!=null && !phoneNo.isEmpty()){ 
                                            return delivery.getDriverPhoneNo().equals(phoneNo);
                                        } else {
                                            return true;
                                        }
                })
                .collect(Collectors.toList());
    }
    
    public List<Delivery> getByTruck(String type, String truckNumber, String trailerOrCaravanNumber) {
        return deliverysList = this.getAll().parallelStream()
                .filter((delivery) -> { if(type!=null && !type.isEmpty()){ 
                                            return delivery.getTruckType().equals(type);
                                        } else {
                                            return true;
                                        }
                })
                .filter((delivery) -> { if(truckNumber!=null && !truckNumber.isEmpty()){ 
                                            return delivery.getTruckNumber().equals(truckNumber);
                                        } else {
                                            return true;
                                        }   
                })
                .filter((delivery) -> { if(trailerOrCaravanNumber!=null && !trailerOrCaravanNumber.isEmpty()){ 
                                            return delivery.getTrailerOrCaravanNumber().equals(trailerOrCaravanNumber);
                                        } else {
                                            return true;
                                        }
                })
                .collect(Collectors.toList());
    }
    
    private Long getIdDefaultPlace(){
        Storageplace defaultPlace = (Storageplace) getEntityManager().createQuery("from Storageplace WHERE place_no = :placeNo AND name = :name")
                                    .setParameter("placeNo", DEFAULT_PLACE_NO).setParameter("name", DEFAULT_PLACE_NAME).getSingleResult();
        return defaultPlace.getPlaceId();
    }

    public Long createAndGetId(Delivery entity) {
        EntityManager em = getEntityManager();
        em.persist(entity);
        return entity.getDeliveryId();
    }
    
    public Delivery startProcessDelivery(){
        Delivery newDelivery = new Delivery();
        newDelivery.getPlace().setPlaceId( this.getIdDefaultPlace() );
        newDelivery.setStatus( DeliveryStatus.OK.getStatus() );
        newDelivery.setCreateDate( LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) );        
        newDelivery.setDeliveryId( this.createAndGetId(newDelivery) );
        return newDelivery;
    }
    
    public boolean closeDelivery(Long id) {
        try{
            Delivery deliveryToClosing = getOneById(id);
            if(deliveryToClosing.getStatus().equals(DeliveryStatus.RE.getStatus())){
                deliveryToClosing.setStatus( DeliveryStatus.FI.getStatus() );
                deliveryToClosing.setFinishDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                this.update(deliveryToClosing);
            } else {
                return false;
            }
        } catch (Exception e){
            return false;
        }
        return true;
    }

}
