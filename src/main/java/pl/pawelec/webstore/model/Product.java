/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webstore.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author mirek
 */
public class Product implements Serializable {
    private Long productId;
    private String productNo;
    private String name;
    private String manufacturer;
    private String category;
    private String description;
    private BigDecimal unitPrice;
    private Integer quantityInBox;
    private String status;
    private LocalDateTime createDate;

    public Product() {
    }

    public Product(Long productId, String productNo, String name, String manufacturer, String category, String description, BigDecimal price, Integer quantity, String status, LocalDateTime createDate) {
        this.productId = productId;
        this.productNo = productNo;
        this.name = name;
        this.manufacturer = manufacturer;
        this.category = category;
        this.description = description;
        this.unitPrice = price;
        this.quantityInBox = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantityInBox() {
        return quantityInBox;
    }

    public void setQuantityInBox(Integer quantityInBox) {
        this.quantityInBox = quantityInBox;
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
        hash = 29 * hash + Objects.hashCode(this.productId);
        hash = 29 * hash + Objects.hashCode(this.productNo);
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
        final Product other = (Product) obj;
        if (!Objects.equals(this.productNo, other.productNo)) {
            return false;
        }
        if (!Objects.equals(this.productId, other.productId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", productNo=" + productNo + ", name=" + name + ", manufacturer=" + manufacturer + ", category=" + category + ", description=" + description + ", unitPrice=" + unitPrice + ", quantityInBox=" + quantityInBox + ", status=" + status + ", createDate=" + createDate + '}';
    }
    
}
