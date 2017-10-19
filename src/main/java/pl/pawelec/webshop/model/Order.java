/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import pl.pawelec.webshop.converter.TimestampToLocalDateTimeConverter;
import pl.pawelec.webshop.model.enum_.OrderStatus;

/**
 *
 * @author mirek
 */
@Entity
@Table(name = "orders")
public class Order implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long orderId;
    
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id", unique = true)
    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, optional = false)
    private Cart cart;
    
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
    
    @JoinColumn(name = "shipping_detail_id", referencedColumnName = "sd_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private ShippingDetail shippingDetail;
    
    @Column(length = 2)
    private String status;
    
    @Convert(converter = TimestampToLocalDateTimeConverter.class)
    @Column(name = "lm_date")
    private LocalDateTime lastModyficationDate;
    
    @Convert(converter = TimestampToLocalDateTimeConverter.class)
    @Column(name = "c_date")
    private LocalDateTime createDate;

    
    
    public Order() {
        this.cart = new Cart();
        this.customer = new Customer();
        this.shippingDetail = new ShippingDetail();
        this.status = OrderStatus.ED.name();
        this.lastModyficationDate = LocalDateTime.now();
        this.createDate = LocalDateTime.now();
    }
    
    public Order(Cart cart, Customer customer, ShippingDetail shippingDetail){
        this();
        this.cart = cart;
        this.customer = customer;
        this.shippingDetail = shippingDetail;
    }
    
    
    
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Valid
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Valid
    public ShippingDetail getShippingDetail() {
        return shippingDetail;
    }

    public void setShippingDetail(ShippingDetail shippingDetail) {
        this.shippingDetail = shippingDetail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getLastModyficationDate() {
        return lastModyficationDate;
    }

    public void setLastModyficationDate(LocalDateTime lastModyficationDate) {
        this.lastModyficationDate = lastModyficationDate;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.orderId);
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
        final Order other = (Order) obj;
        if (!Objects.equals(this.orderId, other.orderId)) {
            return false;
        }
        return true;
    }

       
    
    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId
                        + ", cartId=" + cart.getCartId()
                        + ", customerId=" + customer.getCustomerId()
                        + ", shippingDetailId=" + shippingDetail.getShippingDetailId()
                        + ", status=" + status 
                        + ", lastModyficationDate=" + lastModyficationDate
                        + ", createDate=" + createDate + '}';
    }

}
