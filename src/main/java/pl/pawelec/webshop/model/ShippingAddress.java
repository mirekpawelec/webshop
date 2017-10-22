/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
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
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import pl.pawelec.webshop.converter.DateToLocalDateConverter;
import pl.pawelec.webshop.converter.TimestampToLocalDateTimeConverter;

/**
 *
 * @author mirek
 */
@Entity
@Table(name = "shipping_address")
public class ShippingAddress implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ship_address_id", nullable = false)
    private Long shippingAddressId;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;
        
    @Convert(converter = DateToLocalDateConverter.class)
    @Column(name = "shipping_date")
    private LocalDate shippingDate;
    
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Address address;
    
    @Convert(converter = TimestampToLocalDateTimeConverter.class)
    @Column(name = "c_date")
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "shippingAddress", fetch = FetchType.EAGER)
    private Set<Order> orderSet;
    
    
    
    public ShippingAddress() {
        this.address = new Address();
        this.createDate = LocalDateTime.now();
    }

    
    
    public Long getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(Long shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    @NotEmpty(message = "{NotEmpty.ShippingAddress.name.validation}")
    @Size(max = 100, message = "{Size.ShippingAddress.name.validation}")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @NotEmpty(message = "{NotEmpty.ShippingAddress.phoneNumber.validation}")
    @Pattern(regexp = "^[+][0-9]{2}[ ][0-9]{3}[ ][0-9]{3}[ ][0-9]{3}$", message = "{Pattern.ShippingAddress.phoneNumber.validation}")
    @Size(max = 15, message = "{Size.ShippingAddress.phoneNumber.validation}")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    @Valid
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Set<Order> getOrderSet() {
        return orderSet;
    }

    public void setOrderSet(Set<Order> orderSet) {
        this.orderSet = orderSet;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.shippingAddressId);
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
        final ShippingAddress other = (ShippingAddress) obj;
        if (!Objects.equals(this.shippingAddressId, other.shippingAddressId)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "ShippingDetail{" 
                + " shippingAddressId=" + shippingAddressId 
                + ", name=" + name
                + ", shippingDate=" + shippingDate 
                + ", shippingAddress=" + address 
                + ", createDate=" + createDate 
                + '}';
    }

}
