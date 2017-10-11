/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import pl.pawelec.webshop.converter.TimestampToLocalDateTimeConverter;
import pl.pawelec.webshop.model.enum_.AddressStatus;

/**
 *
 * @author mirek
 */
@Entity
public class Address implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", nullable = false)
    private Long addressId;
    
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer; 
    
    @Column(name = "door_no", nullable = false, length = 10)
    private String doorNo;
    
    @Column(name = "street_name", nullable = false, length = 25)
    private String streetName;
    
    @Column(name = "area_name", nullable = false, length = 25)
    private String areaName;
    
    @Column(nullable = false, length = 25)
    private String state;
    
    @Column(nullable = false, length = 25)
    private String country;
    
    @Column(name = "zip_code", nullable = false, length = 10)
    private String zipCode;

    @Column(length = 2)
    private String status;
    
    @Convert(converter = TimestampToLocalDateTimeConverter.class)
    @Column(name = "c_date")
    private LocalDateTime createDate;
   
    
    
    @OneToOne(mappedBy = "address", fetch = FetchType.EAGER)
    private Order order;
    
    
    
    public Address() {
        this.customer = new Customer();
        this.status = AddressStatus.OK.name();
        this.createDate = LocalDateTime.now();
    }
    
    public Address(Builder builder) {
        this.addressId=builder.addressId;
        this.doorNo=builder.doorNo;
        this.streetName=builder.streetName;
        this.areaName=builder.areaName;
        this.state=builder.state;
        this.country=builder.country;
        this.zipCode=builder.zipCode;
        this.status=builder.status;
        this.createDate=builder.createDate;
    }

    
    
    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(String doorNo) {
        this.doorNo = doorNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Order getOrder() {
        return order;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.addressId);
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
        final Address other = (Address) obj;
        if (!Objects.equals(this.addressId, other.addressId)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "Address{addressId=" + addressId + ", customer=" + customer + ", doorNo=" + doorNo + ", streetName=" + streetName 
             + ", areaName=" + areaName + ", state=" + state + ", country=" + country + ", zipCode=" + zipCode 
             + ", status=" + status + ", createDate=" + createDate + ", order=" + order + '}';
    
    }

    
    
    public static class Builder{
        private Long addressId;
        private Customer customer;
        private String doorNo;
        private String streetName;
        private String areaName;
        private String state;
        private String country;
        private String zipCode;
        private String status;
        private LocalDateTime createDate;
        
        public Builder withAddressId(Long addressId){
            this.addressId=addressId;
            return this;
        }
        public Builder withCustomer(Customer customer){
            this.customer=customer;
            return this;
        }
        public Builder withDoorNo(String doorNo){
            this.doorNo=doorNo;
            return this;
        }
        public Builder withStreetName(String streetName){
            this.streetName=streetName;
            return this;
        }
        public Builder withAreaName(String areaName){
            this.areaName=areaName;
            return this;
        }
        public Builder withState(String state){
            this.state=state;
            return this;
        }
        public Builder withCountry(String country){
            this.country=country;
            return this;
        }
        public Builder withZipCode(String zipCode){
            this.zipCode=zipCode;
            return this;
        }
        public Builder withStatus(String status){
            this.status=status;
            return this;
        }
        public Builder withCreateDate(LocalDateTime createDate){
            this.createDate=createDate;
            return this;
        }
        public Address build(){
            return new Address(this);
        }
    } 
}
