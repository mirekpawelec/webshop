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
import pl.pawelec.webshop.converter.TimestampToLocalDateTimeConverter;

/**
 *
 * @author mirek
 */
@Entity
@Table(name = "repository")
public class Repository implements Serializable{
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "loadunit_id", nullable = false)
    private Long loadunitId;
    
    @Column(name = "loadunit_no", nullable = false, unique = true, length = 10)
    private String loadunitNo;
    
    @JoinColumn(name = "product_id", referencedColumnName = "product_id") 
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
    
    @Column(nullable = false)
    private Integer quantity;
    
    @JoinColumn(name = "place_id", referencedColumnName = "place_id") 
    @ManyToOne(fetch = FetchType.EAGER)
    private Storageplace place;
    
    /**
     *  if new, used, refurbished 
     */
    @Column(nullable = false, length = 25)
    private String state;
    
    @Column(nullable = false)
    private Integer qualityStatus;
    
    @Column(nullable = false, length = 2)
    private String status;
    
    @Column(name = "lm_date")
    @Convert(converter = TimestampToLocalDateTimeConverter.class)
    private LocalDateTime lastModifikationDate;
    
    @Column(name = "c_date")
    @Convert(converter = TimestampToLocalDateTimeConverter.class)
    private LocalDateTime createDate;
         
    
    
    public Repository() {
        product = new Product();
        place = new Storageplace();
    }

    
    
    public Long getLoadunitId() {
        return loadunitId;
    }

    public void setLoadunitId(Long loadunitId) {
        this.loadunitId = loadunitId;
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

    public Storageplace getPlace() {
        return place;
    }

    public void setPlace(Storageplace place) {
        this.place = place;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getQualityStatus() {
        return qualityStatus;
    }

    public void setQualityStatus(Integer qualityStatus) {
        this.qualityStatus = qualityStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getLastModifikationDate() {
        return lastModifikationDate;
    }

    public void setLastModifikationDate(LocalDateTime lastModifikationDate) {
        this.lastModifikationDate = lastModifikationDate;
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
        hash = 97 * hash + Objects.hashCode(this.loadunitId);
        hash = 97 * hash + Objects.hashCode(this.product);
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
        final Repository other = (Repository) obj;
        if (!Objects.equals(this.loadunitId, other.loadunitId)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return "Repository{" + "loadunitId=" + loadunitId + ", loadunitNo=" + loadunitNo + ", product=" + product + ", quantity=" + quantity + ", place=" + place + ", state=" + state + ", qualityStatus=" + qualityStatus + ", status=" + status + ", lastModifikationDate=" + lastModifikationDate + ", createDate=" + createDate + '}';
    }
    
}
