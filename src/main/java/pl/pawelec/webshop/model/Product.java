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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;
import pl.pawelec.webshop.converter.TimestampToLocalDateTimeConverter;
import pl.pawelec.webshop.model.enum_.ProductStatus;
import pl.pawelec.webshop.validator.ProductNo;

/**
 *
 * @author mirek
 */
@Entity
@Table(name = "product" )
public class Product implements Serializable{
    private static final long serialVersionUID = 1L;
    public interface newForm{}
    public interface updateForm{}
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "product_id")
    private Long productId;
    
    @Column(name = "product_no", unique = true)
    private String productNo;
    
    @Column(nullable = false, length = 50)
    private String name;
    
    @Column(nullable = false, length = 50)
    private String manufacturer;
    
    @Column(nullable = false, length = 25)
    private String category;
    
    @Lob
    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false, precision = 9, scale = 2)
    private BigDecimal unitPrice;
    
    @Column(nullable = false, precision = 4, scale = 0)
    private Integer quantityInBox;
    
    @Column(name = "is_promotion", length = 1)
    private String promotion;
    
    @Column(precision = 3, scale = 0)
    private Integer discount;
    
    @Column(length = 2)
    private String status;
    
    @Convert(converter = TimestampToLocalDateTimeConverter.class)
    @Column(name = "c_date")
    private LocalDateTime createDate;
    
    @Transient
    private MultipartFile productImage;
    
    @Transient
    private MultipartFile productUserManual;
    
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private Set<Repository> repositorySet = new HashSet<Repository>();
    
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private Set<DeliveryItem> deliveryItemSet = new HashSet<DeliveryItem>();
    
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private Set<CartItem> cartItemSet = new HashSet<CartItem>();
    
    
    
    public Product(){
        this.status = ProductStatus.ED.name();
        this.createDate = LocalDateTime.now();
    }

    
    
    public Product(Builder builder) {
        this.productId = builder.productId;
        this.productNo = builder.productNo;
        this.name = builder.name;
        this.manufacturer = builder.manufacturer;
        this.category = builder.category;
        this.description = builder.description;
        this.unitPrice = builder.unitPrice;
        this.quantityInBox = builder.quantityInBox;
        this.promotion = builder.promotion;
        this.discount = builder.discount;
        this.status = builder.status;
        this.createDate = builder.createDate;
        this.repositorySet = builder.repositorySet;
        this.deliveryItemSet = builder.deliveryItemSet;
    }
    
    public boolean isNew(){
        return (this.productId==null);
    }
    
    public Long getProductId() {
        return productId;
    }
    
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    
    @NotEmpty(groups = {newForm.class}, message = "{NotEmpty.Product.productNo.validation}")
    @Pattern(groups = {newForm.class}, regexp = "[0-9]{3}[.]{1}[0-9]{3}[.]{1}[0-9]{2}", message = "{Pattern.Product.productNo.validation}")
    @ProductNo(groups = {newForm.class})
    public String getProductNo() {
        return productNo;
    }
    
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }
    
    @NotEmpty(groups = {newForm.class, updateForm.class}, message = "{NotEmpty.Product.name.validation}")
    @Size(groups = {newForm.class, updateForm.class}, max = 50, message = "{Size.Product.name.validation}")
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @NotEmpty(groups = {newForm.class, updateForm.class}, message = "{NotEmpty.Product.manufacturer.validation}")
    @Size(groups = {newForm.class, updateForm.class}, max = 50, message = "{Size.Product.manufacturer.validation}")
    public String getManufacturer() {
        return manufacturer;
    }
    
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    
    @NotEmpty(groups = {newForm.class, updateForm.class}, message = "{NotEmpty.Product.category.validation}")
    @Size(groups = {newForm.class, updateForm.class}, max = 25, message = "{Size.Product.category.validation}")
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    @NotEmpty(groups = {newForm.class, updateForm.class}, message = "{NotEmpty.Product.description.validation}")
    @Size(groups = {newForm.class, updateForm.class}, max = 65535, message = "{Size.Product.description.validation}")
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @NotNull(groups = {newForm.class, updateForm.class}, message = "{NotNull.Product.unitPrice.validation}")
    @Min(groups = {newForm.class, updateForm.class}, value = 1, message = "{Min.Product.unitPrice.validation}")
    @Digits(groups = {newForm.class, updateForm.class}, integer = 7, fraction = 2, message = "{Digits.Product.unitPrice.validation}")
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    @NotNull(groups = {newForm.class, updateForm.class}, message = "{NotNull.Product.quantityInBox.validation}")
    @Min(groups = {newForm.class, updateForm.class}, value = 1, message = "{Min.Product.quantityInBox.validation}")
    @Digits(groups = {newForm.class, updateForm.class}, integer = 4, fraction = 0, message = "{Digits.Product.quantityInBox.validation}")
    public Integer getQuantityInBox() {
        return quantityInBox;
    }
    
    public void setQuantityInBox(Integer quantityInBox) {
        this.quantityInBox = quantityInBox;
    }

    @Size(groups = {newForm.class, updateForm.class}, max = 1, message = "{Size.Product.promotion.validation}")
    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }
    
    @Min(groups = {newForm.class, updateForm.class}, value = 0, message = "{Min.Product.discount.validation}")
    @Digits(groups = {newForm.class, updateForm.class}, integer = 3, fraction = 0, message = "{Digits.Product.discount.validation}")
    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @JsonIgnore
    public LocalDateTime getCreateDate() {
        return createDate;
    }
    
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
    
    @JsonIgnore
    public MultipartFile getProductImage() {
        return productImage;
    }
    
    public void setProductImage(MultipartFile productImage) {
        this.productImage = productImage;
    }
    
    @JsonIgnore
    public MultipartFile getProductUserManual() {
        return productUserManual;
    }
    
    public void setProductUserManual(MultipartFile productUserManual) {
        this.productUserManual = productUserManual;
    }
    
    @JsonIgnore
    public Set<Repository> getRepositorySet() {
        return repositorySet;
    }

    public void setRepositorySet(Set<Repository> repositorySet) {
        this.repositorySet = repositorySet;
    }
    
    @JsonIgnore
    public Set<DeliveryItem> getDeliveryItemSet() {
        return deliveryItemSet;
    }

    public void setDeliveryItemSet(Set<DeliveryItem> deliveryItemSet) {
        this.deliveryItemSet = deliveryItemSet;
    }

    @JsonIgnore
    public Set<CartItem> getCartItemSet() {
        return cartItemSet;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.productNo);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.manufacturer);
        hash = 97 * hash + Objects.hashCode(this.category);
        hash = 97 * hash + Objects.hashCode(this.unitPrice);
        hash = 97 * hash + Objects.hashCode(this.quantityInBox);
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
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.manufacturer, other.manufacturer)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (!Objects.equals(this.unitPrice, other.unitPrice)) {
            return false;
        }
        if (!Objects.equals(this.quantityInBox, other.quantityInBox)) {
            return false;
        }
        return true;
    }
    
    
    
    @Override
    public String toString() {
        return "Product{" 
                + "productId=" + productId 
                + ", productNo=" + productNo 
                + ", name=" + name 
                + ", manufacturer=" + manufacturer 
                + ", category=" + category 
                + ", unitPrice=" + unitPrice // + '}';
                + ", quantityInBox=" + quantityInBox 
                + ", promotion=" + promotion 
                + ", discount=" + discount
                + ", status=" + status 
                + ", createDate=" + createDate
                + '}';   
    } 
    
    
    
        public static class Builder{
            private Long productId;
            private String productNo;
            private String name;
            private String manufacturer;
            private String category;
            private String description;
            private BigDecimal unitPrice;
            private Integer quantityInBox;
            private String promotion;
            private Integer discount;
            private String status;
            private LocalDateTime createDate;
            private Set<Repository> repositorySet;
            private Set<DeliveryItem> deliveryItemSet;
                    
            public Builder withProductId(Long productId){
                this.productId=productId;
                return this;
            }
            public Builder withProductNo(String productNo){
                this.productNo=productNo;
                return this;
            }
            public Builder withName(String name){
                this.name=name;
                return this;
            }
            public Builder withManufacturer(String manufacturer){
                this.manufacturer=manufacturer;
                return this;
            }
            public Builder withCategory(String category){
                this.category=category;
                return this;
            }
            public Builder withDescription(String description){
                this.description=description;
                return this;
            }
            public Builder withUnitPrice(BigDecimal unitPrice){
                this.unitPrice=unitPrice;
                return this;
            }
            public Builder withQuantityInBox(Integer quantityInBox){
                this.quantityInBox=quantityInBox;
                return this;
            }
            public Builder withPromotion(String promotion){
                this.promotion=promotion;
                return this;
            }
            public Builder withDiscount(Integer discount){
                this.discount=discount;
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
            public Builder withRepositorySet(Set<Repository> repositorySet){
                this.repositorySet=repositorySet;
                return this;
            }
            public Builder withDeliveryItemSet(Set<DeliveryItem> deliveryItemSet){
                this.deliveryItemSet=deliveryItemSet;
                return this;
            }
            public Product build(){
                return new Product(this);
            }
        }
    
}
