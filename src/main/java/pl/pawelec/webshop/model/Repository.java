/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author mirek
 */
public class Repository {
    private Long loadunitId;
    private Long productId;
    private Integer quantity;
    private Long placeId;
    private Short conditions;
    private Short qualityStatus;
    private String status;
    private LocalDateTime lastModifikationDate;
    private LocalDateTime createDate;

    public Repository() {
    }

    public Long getLoadunitId() {
        return loadunitId;
    }

    public void setLoadunitId(Long loadunitId) {
        this.loadunitId = loadunitId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public Short getConditions() {
        return conditions;
    }

    public void setConditions(Short conditions) {
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
