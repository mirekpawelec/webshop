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
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import pl.pawelec.webshop.converter.TimestampToLocalDateTimeConverter;
import pl.pawelec.webshop.model.enum_.DeliveryStatus;
import pl.pawelec.webshop.model.enum_.ProductStatus;

/**
 *
 * @author mirek
 */
@Entity
@Table(name = "delivery_item")
public class DeliveryItem implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", nullable = false)
    private Long itemId;
    
    @JoinColumn(name = "delivery_id", referencedColumnName = "delivery_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Delivery delivery;
    
    @Column(name = "loadunit_no", nullable = false, unique = true, length = 10)
    private String loadunitNo;
        
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
    
    @Column(nullable = false)
    private Integer quantity;
    
    @Column(length = 2)
    private String status;
    
    @Column(name = "c_date")
    @Convert(converter = TimestampToLocalDateTimeConverter.class)
    private LocalDateTime createDate;

    
    
    public DeliveryItem() {
        this.delivery = new Delivery();
        this.product = new Product();
        this.status = ProductStatus.ED.name();
        this.createDate = LocalDateTime.now();
    }

    
    
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    @NotEmpty(message = "NotEmpty.DeliveryItem.loadunitNo.validation")
    @Pattern(regexp = "[0-9]{10}", message = "Pattern.DeliveryItem.loadunitNo.validation")
    @Size(max = 10, message = "Size.DeliveryItem.loadunitNo.validation")
    public String getLoadunitNo() {
        return loadunitNo;
    }

    public void setLoadunitNo(String loadunitNo) {
        this.loadunitNo = loadunitNo;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @NotNull(message = "NotNull.DeliveryItem.quantity.validation")
    @Min(value = 1, message = "Min.DeliveryItem.quantity.validation")
    @Digits(integer = 2, fraction = 0, message = "Digits.DeliveryItem.quantity.validation")
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.itemId);
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
        final DeliveryItem other = (DeliveryItem) obj;
        if (!Objects.equals(this.itemId, other.itemId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DeliveryItem{" 
                + " itemId=" + itemId 
                + ", deliveryId=" + delivery.getDeliveryId() 
                + ", loadunitNo=" + loadunitNo 
                + ", product=" + product 
                + ", quantity=" + quantity 
                + ", status=" + status 
                + ", createDate=" + createDate + '}';
    }
    
}
