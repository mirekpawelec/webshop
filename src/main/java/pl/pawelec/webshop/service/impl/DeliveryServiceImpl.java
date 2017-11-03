/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service.impl;

import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.webflow.execution.RequestContext;
import pl.pawelec.webshop.exception.InvalidDeliveryException;
import pl.pawelec.webshop.model.Delivery;
import pl.pawelec.webshop.model.DeliveryItem;
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
        if(delivery.getStatus().equals(DeliveryStatus.OK.name())){
            deliveryDao.delete(delivery);
        }
    }

    @Override
    public void deleteById(Long id) {
        deliveryDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        deliveryDao.deleteAll();
    }

    @Override
    public Delivery getOneById(Long id) {
        return deliveryDao.getOneById(id);
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
    public boolean exists(Long id) {
        return deliveryDao.exists(id);
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
        Delivery delivery = new Delivery();
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
    public String closeDelivery(Long id) {
        if(deliveryDao.closeDelivery(id)){
            return "true";
        } else {
            return "false";
        }
    }
    
    public Delivery setPlaceIdAccordingToPlaceNo(Delivery delivery, List<Storageplace> storageplaces){
        storageplaces.forEach( storageplace -> {
            if( storageplace.getPlaceNo().equals( delivery.getPlace().getPlaceNo() )){
                delivery.getPlace().setPlaceId( storageplace.getPlaceId() );
            }
        });
        return delivery;
    }
    
    public String saveDetailsDelivery(Delivery delivery){
        try{
            if(delivery.getPlace().getPlaceNo()!=null && !delivery.getPlace().getPlaceNo().equals("NONE")){
                delivery.getPlace().setPlaceId( storageplaceService.getByPlaceNo( delivery.getPlace().getPlaceNo() ).getPlaceId() );
            }
            if(!delivery.getStatus().equals("FI")){
                delivery.setStatus(DeliveryStatus.RE.name());
                this.update(delivery);
            } else {
                throw new IllegalArgumentException("It can't update closed delivery!");
            }
        } catch(Exception e){
            return "false";
        }
        return "true";
    }

    @Override
    public void deleteByIdAndStatus(Long id, String status) {
        if(status.equals("OK")){
            this.deleteById(id);
        }
    }
    
    @Override
    public String setWhereComeFrom(String view) {
        return view;
    }
    
    @Override
    public String whatView(String view) {
        return view;
    }

    public void setFlowModelAttribute(RequestContext context){
        HttpServletRequest req = (HttpServletRequest)context.getExternalContext().getNativeRequest(); 
        String url = context.getFlowExecutionUrl();
        url = url.substring(url.indexOf("/", 1), url.length()) + "&";
//        System.out.println(url);
        req.getSession().setAttribute("lastRequestUrl", url);
    }
    
}
