/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import pl.pawelec.webshop.converter.TimestampToLocalDateTimeConverter;

/**
 *
 * @author mirek
 */
@Entity
@Table(name = "shipping_details")
public class ShippingDetails implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ship_detail_id", nullable = false)
    private Long shippingDetailId;
    
    @Column(name = "payment_method", length = 50)
    private String paymentMethod;
    
    @Column(name = "payment_cost", scale = 6, precision = 2)
    private BigDecimal paymentCost;
    
    @Column(name = "delivery_method", length = 50)
    private String deliveryMethod;
    
    @Column(name = "delivery_cost", scale = 6, precision = 2)
    private BigDecimal deliveryCost;
    
    @Convert(converter = TimestampToLocalDateTimeConverter.class)
    @Column(name = "c_date")
    private LocalDateTime createDate;
    
    @Transient
    @Column(name = "total_cost", scale = 6, precision = 2)
    private BigDecimal totalCost;
    
    @OneToMany(mappedBy = "shippingDetails", fetch = FetchType.EAGER)
    private Set<Order> orderSet;
    
    
    
    public ShippingDetails() {
        this.paymentCost = new BigDecimal(0);
        this.deliveryCost = new BigDecimal(0);
        this.totalCost = new BigDecimal(0);
        this.createDate = LocalDateTime.now();
    }

    
    
    public Long getShippingDetailId() {
        return shippingDetailId;
    }

    public void setShippingDetailId(Long shippingDetailId) {
        this.shippingDetailId = shippingDetailId;
    }
    
    @NotEmpty(message = "{NotEmpty.ShippingDetails.paymentMethod.validation}")
    @Size(max = 50, message = "{Size.ShippingDetails.paymentMethod.validation}")
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    @NotNull(message = "{NotNull.ShippingDetails.paymentCost.validation}")
    @Min(value = 0, message = "{Min.ShippingDetails.paymentCost.validation}")
    @Digits(integer = 6, fraction = 2, message = "{Digits.ShippingDetails.paymentCost.validation}")
    public BigDecimal getPaymentCost() {
        return paymentCost;
    }

    public void setPaymentCost(BigDecimal paymentCost) {
        this.paymentCost = paymentCost;
    }

    @NotEmpty(message = "{NotEmpty.ShippingDetails.deliveryMethod.validation}")
    @Size(max = 50, message = "{Size.ShippingDetails.deliveryMethod.validation}")
    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    @NotNull(message = "{NotNull.ShippingDetails.deliveryCost.validation}")
    @Min(value = 0, message = "{Min.ShippingDetails.deliveryCost.validation}")
    @Digits(integer = 6, fraction = 2, message = "{Digits.ShippingDetails.deliveryCost.validation}")
    public BigDecimal getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(BigDecimal deliveryCost) {
        this.deliveryCost = deliveryCost;
    }
    
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
    
    public BigDecimal getTotalCost() {
        return totalCost;
    }
    
    public void updateTotalCost(){
       totalCost = paymentCost.add(deliveryCost);
    }

    public Set<Order> getOrderSet() {
        return orderSet;
    }

    public void setOrderSet(Set<Order> orderSet) {
        this.orderSet = orderSet;
    }

  
    
    @Override
    public String toString() {
        return "ShippingDetails{" 
                + " shippingId=" + shippingDetailId 
                + ", paymentMethod=" + paymentMethod 
                + ", paymentCost=" + paymentCost 
                + ", deliveryMethod=" + deliveryMethod 
                + ", deliveryCost=" + deliveryCost
                + ", totalCost=" + totalCost 
                + ", createDate=" + createDate 
                + '}';
    }

}
