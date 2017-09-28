/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import pl.pawelec.webshop.converter.TimestampToLocalDateTimeConverter;
import pl.pawelec.webshop.converter.StringToLocalDataTimeConverter;

/**
 *
 * @author mirek
 */
@Entity
@Table(name = "delivery")
public class Delivery implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id", nullable = false)
    private Long deliveryId;
    
    @JoinColumn(name = "place_id", referencedColumnName = "place_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Storageplace place;
    
    @Column(length = 255)
    private String description;
    
    @Column(name = "driver_name1", length = 25)
    private String driverFirstName;
    
    @Column(name = "driver_name2", length = 25)
    private String driverLastName;
    
    @Column(name = "driver_phone", length = 25)
    private String driverPhoneNo;
    
    @Column(name = "truck_type", length = 25)
    private String truckType;
    
    @Column(name = "truck_number1", length = 25)
    private String truckNumber;
    
    @Column(name = "truck_number2", length = 25)
    private String trailerOrCaravanNumber;
    
    @Column(name = "c_user", length = 50)
    private String createUser;
    
    @Column(length = 2)
    private String status;
    
    @Column(name = "c_date")
    //@Convert(converter = LocalDateTimeConverter.class)
//    @Convert(converter = StringToLocalDataTimeConverter.class)
    private String createDate;
    
    @Column(name = "f_date")
    //@Convert(converter = LocalDateTimeConverter.class)
//    @Convert(converter = StringToLocalDataTimeConverter.class)
    private String finishDate;
    
    
    
    @OneToMany(mappedBy = "delivery", fetch = FetchType.EAGER)
    private Set<DeliveryItem> deliveryItemSet = new HashSet<DeliveryItem>();

    
    
    public Delivery() {
        place = new Storageplace();
    }

    
    
    public Delivery(Long deliveryId, Storageplace place, String description) {
        this.deliveryId = deliveryId;
        this.place = place;
        this.description = description;
    }
   
    
    
    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Storageplace getPlace() {
        return place;
    }

    public void setPlace(Storageplace place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDriverFirstName() {
        return driverFirstName;
    }

    public void setDriverFirstName(String driverFirstName) {
        this.driverFirstName = driverFirstName;
    }

    public String getDriverLastName() {
        return driverLastName;
    }

    public void setDriverLastName(String driverLastName) {
        this.driverLastName = driverLastName;
    }

    public String getDriverPhoneNo() {
        return driverPhoneNo;
    }

    public void setDriverPhoneNo(String driverPhoneNo) {
        this.driverPhoneNo = driverPhoneNo;
    }

    public String getTruckType() {
        return truckType;
    }

    public void setTruckType(String truckType) {
        this.truckType = truckType;
    }

    public String getTruckNumber() {
        return truckNumber;
    }

    public void setTruckNumber(String truckNumber) {
        this.truckNumber = truckNumber;
    }

    public String getTrailerOrCaravanNumber() {
        return trailerOrCaravanNumber;
    }

    public void setTrailerOrCaravanNumber(String trailerOrCaravanNumber) {
        this.trailerOrCaravanNumber = trailerOrCaravanNumber;
    }

    public Set<DeliveryItem> getDeliveryItemSet() {
        return deliveryItemSet;
    }

    public void setDeliveryItemSet(Set<DeliveryItem> deliveryItemSet) {
        this.deliveryItemSet = deliveryItemSet;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.deliveryId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Delivery other = (Delivery) obj;
        if (!Objects.equals(this.deliveryId, other.deliveryId)) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return "Delivery{" + "deliveryId=" + deliveryId + ", place=" + place + ", description=" + description 
             + ", driverFirstName=" + driverFirstName + ", driverLastName=" + driverLastName + ", driverPhoneNo=" + driverPhoneNo 
             + ", truckType=" + truckType + ", truckNumber=" + truckNumber + ", trailerOrCaravanNumber=" + trailerOrCaravanNumber 
             + ", createUser=" + createUser + ", status=" + status + ", createDate=" + createDate + ", finishDate=" + finishDate 
             + ", deliveryItemSet=" + deliveryItemSet.size() + '}';
    }

}
