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
import javax.persistence.Transient;
import pl.pawelec.webshop.converter.TimestampToLocalDateTimeConverter;

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
        delivery = new Delivery();
        product = new Product();
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
        return "DeliveryItem{" + "itemId=" + itemId + ", delivery=" + delivery + ", loadunitNo=" + loadunitNo + ", product=" + product + ", quantity=" + quantity + ", status=" + status + ", createDate=" + createDate + '}';
    }
    
}
