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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import pl.pawelec.webshop.converter.TimestampToLocalDateTimeConverter;
import pl.pawelec.webshop.model.enum_.CustomerStatus;

/**
 *
 * @author mirek
 */
@Entity
@Table(name = "customers")
public class Customer implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private Long customerId;
    
    @Column(name = "first_name", nullable = false, length = 25)
    private String firstName;
    
    @Column(name = "last_name", nullable = false, length = 25)
    private String lastName;
    
    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;
    
    @Column(nullable = false, length = 50)
    private String email;

    @JoinColumn(name = "address_id", referencedColumnName = "address_id", unique = true)
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    private Address address; 
    
    @Column(length = 2)
    private String status;
    
    @Convert(converter = TimestampToLocalDateTimeConverter.class)
    @Column(name = "c_date")
    private LocalDateTime createDate;
     
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<Order> orderSet = new HashSet<Order>(); 
    
    @OneToOne(mappedBy = "customer", fetch = FetchType.EAGER)
    private UserInfo user; 
    
    
    
    public Customer() {
        this.address = new Address();
        this.status = CustomerStatus.OK.name();
        this.createDate = LocalDateTime.now();
    }

    public Customer(String firstName, String lastName, String phoneNumber, String email, Address address) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public Customer(Builder builder) {
        this.customerId = builder.customerId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phoneNumber = builder.phoneNumber;
        this.email = builder.email;
        this.address = builder.address;
        this.status = builder.status;
        this.createDate = builder.createDate;
    }

    
    
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @NotEmpty(message = "{NotEmpty.Customer.firstName.validation}")
    @Size(max = 25, message = "{Size.Customer.firstName.validation}")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    @NotEmpty(message = "{NotEmpty.Customer.lastName.validation}")
    @Size(max = 25, message = "{Size.Customer.lastName.validation}")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotEmpty(message = "{NotEmpty.Customer.phoneNumber.validation}")
    @Pattern(regexp = "^[+][0-9]{2}[ ][0-9]{3}[ ][0-9]{3}[ ][0-9]{3}$", message = "{Pattern.Customer.phoneNumber.validation}")
    @Size(max = 15, message = "{Size.Customer.phoneNumber.validation}")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    @NotEmpty(message = "{NotEmpty.Customer.email.validation}")
    @Size(max = 50, message = "{Size.Customer.email.validation}")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Valid
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
    
    public void setOrderSet(Set<Order> orderSet) {
        this.orderSet = orderSet;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
    

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.customerId);
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
        return "Customer{" 
                    + " customerId=" + customerId 
                    + ", firstName=" + firstName 
                    + ", lastName=" + lastName 
                    + ", phoneNumber=" + phoneNumber 
                    + ", email=" + email 
                    + ", address=" + address
                    + ", status=" + status 
                    + ", createDate=" + createDate
                    + '}';
    } 
    
    
    
    public static class Builder{
        private Long customerId;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String email;
        private Address address; 
        private String status;
        private LocalDateTime createDate;
        
        public Builder withCustomerId(Long customerId){
            this.customerId=customerId;
            return this;
        }
        public Builder withFirstName(String firstName){
            this.firstName=firstName;
            return this;
        }
        public Builder withLastName(String lastName){
            this.lastName=lastName;
            return this;
        }
        public Builder withPhoneNumber(String phoneNumber){
            this.phoneNumber=phoneNumber;
            return this;
        }
        public Builder withEmail(String email){
            this.email=email;
            return this;
        }
        public Builder withAddress(Address address){
            this.address=address;
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
        public Customer build(){
            return new Customer(this);
        }
    }
    
}
