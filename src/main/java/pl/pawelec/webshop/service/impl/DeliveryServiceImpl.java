/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pawelec.webshop.exception.InvalidDeliveryException;
import pl.pawelec.webshop.model.Delivery;
import pl.pawelec.webshop.model.enum_.DeliveryStatus;
import pl.pawelec.webshop.model.Storageplace;
import pl.pawelec.webshop.model.dao.DeliveryDao;
import pl.pawelec.webshop.service.DeliveryService;
import pl.pawelec.webshop.service.StorageplaceService;

/**
 *
 * @author mirek
 */
@Service
@Transactional
public class DeliveryServiceImpl implements DeliveryService{
    
    @Autowired
    private DeliveryDao deliveryDao;
    
    @Autowired
    private StorageplaceService storageplaceService;
    
    @Override
    public void create(Delivery delivery) {
        deliveryDao.create(delivery);
    }

    @Override
    public void update(Delivery delivery) {
        deliveryDao.update(delivery);
    }

    @Override
    public void delete(Delivery delivery) {
        deliveryDao.delete(delivery);
    }

    @Override
    public void deleteById(Long Id) {
        deliveryDao.deleteById(Id);
    }

    @Override
    public void deleteAll() {
        deliveryDao.deleteAll();
    }

    @Override
    public Delivery getOneById(Long Id) {
        return deliveryDao.getOneById(Id);
    }

    @Override
    public List<Delivery> getAll() {
        return deliveryDao.getAll();
    }

    @Override
    public Long count() {
        return deliveryDao.count();
    }

    @Override
    public boolean exists(Long Id) {
        return deliveryDao.exists(Id);
    }
    
    @Override
    public List<Delivery> getByDriver(String firstName, String lastName, String phoneNo) {
        return deliveryDao.getByDriver(firstName, lastName, phoneNo);
    }

    @Override
    public List<Delivery> getByTruck(String type, String truckNumber, String trailerOrCaravanNumber) {
        return deliveryDao.getByTruck(type, truckNumber, trailerOrCaravanNumber);
    }

    @Override
    public Delivery startProcessDelivery(String deliveryId) {
        System.out.println("### startProcessDelivery ; deliveryId=" + deliveryId);
        Delivery delivery = new Delivery();
        String delivStatus = null;
        if(deliveryId==null){
            delivery = deliveryDao.startProcessDelivery();
        } else {
            delivery = deliveryDao.getOneById(Long.valueOf(deliveryId));
        }
        if(delivery==null){
            throw new InvalidDeliveryException("It has occured an error while creating a delivery!");
        }
        return delivery;
    }

    @Override
    public String closeDelivery(Long Id) {
        if(deliveryDao.closeDelivery(Id)){
            return "true";
        } else {
            return "false";
        }
    }
    
    public Delivery setPlaceIdIntoDelivery(Delivery delivery, List<Storageplace> storageplaces){
        storageplaces.forEach( storageplace -> {
            if( storageplace.getPlaceNo().equals( delivery.getPlace().getPlaceNo() )){
                delivery.getPlace().setPlaceId( storageplace.getPlaceId() );
            }
        });
        return delivery;
    }
    
    public boolean saveDetailsDelivery(Delivery delivery){
        System.out.println("delivery=" + delivery);
        try{
            if(delivery.getPlace().getPlaceNo()!=null && !delivery.getPlace().getPlaceNo().equals("NONE")){
                delivery.getPlace().setPlaceId( storageplaceService.getByPlaceNo( delivery.getPlace().getPlaceNo() ).getPlaceId() );
            }
            delivery.setStatus(DeliveryStatus.RE.getStatus());
            this.update(delivery);
        } catch(Exception e){
            return false;
        }
        return true;
    }
}
