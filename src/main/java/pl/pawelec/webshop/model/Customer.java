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
import javax.persistence.OneToMany;
import pl.pawelec.webshop.converter.TimestampToLocalDateTimeConverter;
import pl.pawelec.webshop.model.enum_.CustomerStatus;

/**
 *
 * @author mirek
 */
@Entity
public class Customer implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private String customerId;
    
    @Column(name = "first_name", nullable = false, length = 25)
    private String firstName;
    
    @Column(name = "last_name", nullable = false, length = 25)
    private String lastName;
    
    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;
    
    @Column(nullable = false, length = 50)
    private String email;

    @Column(length = 2)
    private String status;
    
    @Convert(converter = TimestampToLocalDateTimeConverter.class)
    @Column(name = "c_date")
    private LocalDateTime createDate;
    
    
    
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<Order> orderSet; 
    
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<Address> addressSet;
    
    
    
    public Customer() {
        this.orderSet = new HashSet<Order>();
        this.addressSet = new HashSet<Address>();
        this.status = CustomerStatus.OK.name();
        this.createDate = LocalDateTime.now();
    }

    public Customer(String customerId, String firstName, String lastName) {
        this();
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    
    
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<Order> getOrderSet() {
        return orderSet;
    }

    public Set<Address> getAddressSet() {
        return addressSet;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.customerId);
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
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.customerId, other.customerId)) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        StringBuilder sba = new StringBuilder();
        addressSet.stream().forEach(a -> sba.append("["+ a +"] "));
        
        StringBuilder sbo = new StringBuilder();
        orderSet.stream().forEach(o -> sbo.append("["+ o +"] "));
        
        return "Customer{" + "customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName 
             + ", phoneNumber=" + phoneNumber + ", email=" + email + ", status=" + status + ", createDate=" + createDate 
             + ", addressSet=" + sba.toString() + ", orderSet=" + sbo.toString() + '}';
    } 
    
}
