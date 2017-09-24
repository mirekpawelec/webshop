/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model;

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
import pl.pawelec.webshop.converter.LocalDateTimeConverter;

/**
 *
 * @author mirek
 */
@Entity
@Table(name = "repository")
public class Repository {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "loadunit_id", nullable = false)
    private Long loadunitId;
    
    @Column(name = "loadunit_no", nullable = false, unique = true, length = 10)
    private String loadunitNo;
    
    @JoinColumn(name = "product_id", referencedColumnName = "product_id") 
    @ManyToOne(fetch = FetchType.EAGER)
    private Product productId;
    
    @Column(nullable = false)
    private Integer quantity;
    
    @JoinColumn(name = "place_id", referencedColumnName = "place_id") 
    @ManyToOne(fetch = FetchType.EAGER)
    private Storageplace placeId;
    
    /**
     *  if new, used, refurbished 
     */
    @Column(nullable = false, length = 25)
    private String conditions;
    
    @Column(nullable = false)
    private Short qualityStatus;
    
    @Column(nullable = false, length = 2)
    private String status;
    
    @Column(name = "lm_date")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime lastModifikationDate;
    
    @Column(name = "c_date")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createDate;
            
    public Repository() {
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
    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Storageplace getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Storageplace placeId) {
        this.placeId = placeId;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public Short getQualityStatus() {
        return qualityStatus;
    }

    public void setQualityStatus(Short qualityStatus) {
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
        hash = 97 * hash + Objects.hashCode(this.productId);
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
        if (!Objects.equals(this.productId, other.productId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Repository{" + "loadunitId=" + loadunitId + ", productId=" + productId + ", quantity=" + quantity + ", placeId=" + placeId + ", conditions=" + conditions + ", qualityStatus=" + qualityStatus + ", status=" + status + ", lastModifikationDate=" + lastModifikationDate + ", createDate=" + createDate + '}';
    }
    
}
