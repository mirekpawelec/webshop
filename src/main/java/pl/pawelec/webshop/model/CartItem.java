/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
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
import pl.pawelec.webshop.model.enum_.CartStatus;

/**
 *
 * @author mirek
 */
@Entity
@Table(name = "cart_items")
public class CartItem implements Serializable{
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(nullable = false)
    private Long id; 
    
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Cart cart;
    
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
    
    @Column(nullable = false)
    private int quantity;
    
    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Column(length = 2)
    private String status;

    @Convert(converter = TimestampToLocalDateTimeConverter.class)
    @Column(name = "lm_date")
    private LocalDateTime lastModificationDate;
    
    @Convert(converter = TimestampToLocalDateTimeConverter.class)
    @Column(name = "c_date")
    private LocalDateTime createDate;



    public CartItem() {
        cart = new Cart();
        product = new Product();
    }

    public CartItem(Product product) {
        this();
        this.product = product;
        this.quantity = 1;
        this.totalPrice = product.getUnitPrice();
        this.status = CartStatus.RE.name();
        this.lastModificationDate = LocalDateTime.now();
        this.createDate = LocalDateTime.now();
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @JsonIgnore
    public LocalDateTime getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(LocalDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }
    
    @JsonIgnore
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }



    public void updateTotalPrice(){
        this.totalPrice = product.getUnitPrice().multiply( BigDecimal.valueOf(quantity) );
    }

    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.id);
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
        final CartItem other = (CartItem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    @Override
    public String toString() {
        return "CartItem{" + "id=" + id + ", cartId=" + cart.getCartId() + ", product=" + product + ", quantity=" + quantity + ", totalPrice=" + totalPrice 
             + ", status=" + status + ", lastModificationDate=" + lastModificationDate + ", createDate=" + createDate + '}';
    }
    
}
