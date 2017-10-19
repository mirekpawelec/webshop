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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import pl.pawelec.webshop.converter.DateToLocalDateConverter;
import pl.pawelec.webshop.converter.TimestampToLocalDateTimeConverter;

/**
 *
 * @author mirek
 */
@Entity
@Table(name = "shipping_detail")
public class ShippingDetail implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sd_id", nullable = false)
    private Long shippingDetailId;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Convert(converter = DateToLocalDateConverter.class)
    @Column(name = "shipping_date")
    private LocalDate shippingDate;
    
    @JoinColumn(name = "shipping_address_id", referencedColumnName = "address_id", unique = true)
    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, optional = false)
    private Address shippingAddress;
    
    @Convert(converter = TimestampToLocalDateTimeConverter.class)
    @Column(name = "c_date")
    private LocalDateTime createDate;

    
    
    @OneToMany(mappedBy = "shippingDetail", fetch = FetchType.EAGER)
    private Set<Order> orderSet;
    
    
    
    public ShippingDetail() {
        this.shippingAddress = new Address();
        this.createDate = LocalDateTime.now();
    }

    public ShippingDetail(String name, Address shippingAddress) {
        this();
        this.name = name;
        this.shippingAddress = shippingAddress;
    }

    
    
    public Long getShippingDetailId() {
        return shippingDetailId;
    }

    public void setShippingDetailId(Long shippingDetailId) {
        this.shippingDetailId = shippingDetailId;
    }

    @NotEmpty(message = "{NotEmpty.ShippingDetail.name.validation}")
    @Size(max = 100, message = "{Size.ShippingDetail.name.validation}")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    @Valid
    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
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
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.shippingDetailId);
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
        final ShippingDetail other = (ShippingDetail) obj;
        if (!Objects.equals(this.shippingDetailId, other.shippingDetailId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ShippingDetail{" 
                + "shippingDetailId=" + shippingDetailId 
                + ", name=" + name 
                + ", shippingDate=" + shippingDate 
                + ", shippingAddress=" + shippingAddress 
                + ", createDate=" + createDate 
                + ", orderSet=" + orderSet
                + '}';
    }

}
