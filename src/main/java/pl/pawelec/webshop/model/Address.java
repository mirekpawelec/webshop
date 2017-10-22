/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import pl.pawelec.webshop.converter.TimestampToLocalDateTimeConverter;
import pl.pawelec.webshop.model.enum_.AddressStatus;

/**
 *
 * @author mirek
 */
@Entity
@Table(name = "address")
public class Address implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", nullable = false)
    private Long addressId;
    
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
    private Customer customer;
    
    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER)
    private Set<ShippingAddress> shippingAddressSet;
    
    
    
    public Address() {
        this.status = AddressStatus.OK.name();
        this.createDate = LocalDateTime.now();
    }
    
    public Address(String doorNo, String streetName, String areaName, String state, String country, String zipCode) {
        this();
        this.doorNo=doorNo;
        this.streetName=streetName;
        this.areaName=areaName;
        this.state=state;
        this.country=country;
        this.zipCode=zipCode;
    }

    
    
    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    @NotEmpty(message = "{NotEmpty.Address.doorNo.validation}")
    @Size(max = 10, message = "{Size.Address.doorNo.validation}")
    public String getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(String doorNo) {
        this.doorNo = doorNo;
    }
    @NotEmpty(message = "{NotEmpty.Address.streetName.validation}")
    @Size(max = 25, message = "{Size.Address.streetName.validation}")
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    @NotEmpty(message = "{NotEmpty.Address.areaName.validation}")
    @Size(max = 25, message = "{Size.Address.areaName.validation}")
    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    @NotEmpty(message = "{NotEmpty.Address.state.validation}")
    @Size(max = 25, message = "{Size.Address.state.validation}")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @NotEmpty(message = "{NotEmpty.Address.country.validation}")
    @Size(max = 25, message = "{Size.Address.country.validation}")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    @NotEmpty(message = "{NotEmpty.Address.zipCode.validation}")
    @Pattern(regexp = "^[0-9]{2}[-][0-9]{3}$", message = "{Pattern.Address.zipCode.validation}")
    @Size(max = 10, message = "{Size.Address.zipCode.validation}")
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<ShippingAddress> getShippingAddressSet() {
        return shippingAddressSet;
    }

    public void setShippingAddressSet(Set<ShippingAddress> shippingAddressSet) {
        this.shippingAddressSet = shippingAddressSet;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.doorNo);
        hash = 59 * hash + Objects.hashCode(this.streetName);
        hash = 59 * hash + Objects.hashCode(this.areaName);
        hash = 59 * hash + Objects.hashCode(this.state);
        hash = 59 * hash + Objects.hashCode(this.country);
        hash = 59 * hash + Objects.hashCode(this.zipCode);
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
        if (!Objects.equals(this.doorNo, other.doorNo)) {
            return false;
        }
        if (!Objects.equals(this.streetName, other.streetName)) {
            return false;
        }
        if (!Objects.equals(this.areaName, other.areaName)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.zipCode, other.zipCode)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "Address{addressId=" + addressId 
                   + ", doorNo=" + doorNo 
                   + ", streetName=" + streetName 
                   + ", areaName=" + areaName 
                   + ", state=" + state 
                   + ", country=" + country 
                   + ", zipCode=" + zipCode 
                   + ", status=" + status 
                   + ", createDate=" + createDate
                   + '}';
    }

}
