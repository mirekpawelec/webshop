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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import pl.pawelec.webshop.converter.TimestampToLocalDateTimeConverter;

/**
 *
 * @author mirek
 */
@Entity
@Table(name = "app_parameter", uniqueConstraints = 
        @UniqueConstraint(columnNames= {"symbol", "name"}))
public class AppParameter implements Serializable{
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parameter_id", nullable = false)
    private Long parameterId;
    
    @Column(nullable = false, length = 50)
    private String symbol;
    
    @Column(nullable = false, length = 50)
    private String name;
    
    @Column(nullable = false, length = 25)
    private String value;
    
    @Column(length = 250)
    private String description;
    
    @Convert(converter = TimestampToLocalDateTimeConverter.class)
    @Column(name = "lm_date")
    private LocalDateTime lastModificationDate;
    
    @Convert(converter = TimestampToLocalDateTimeConverter.class)
    @Column(name = "c_date")
    private LocalDateTime createDate;

    
    
    public AppParameter() {
        this.lastModificationDate = LocalDateTime.now();
        this.createDate = LocalDateTime.now();
    }

    public AppParameter(String symbol, String name, String value, String description) {
        this();
        this.symbol = symbol;
        this.name = name;
        this.value = value;
        this.description = description;
    }
    
    
    
    public boolean isNew(){
        return this.getParameterId()==null;
    }
    
    public Long getParameterId() {
        return parameterId;
    }

    public void setParameterId(Long parameterId) {
        this.parameterId = parameterId;
    }

    @NotEmpty(message = "{NotEmpty.AppParameter.symbol.validation}")
    @Size(max = 50, message = "{Size.AppParameter.symbol.validation}")
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @NotEmpty(message = "{NotEmpty.AppParameter.name.validation}")
    @Size(max = 50, message = "{Size.AppParameter.name.validation}")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty(message = "{NotEmpty.AppParameter.value.validation}")
    @Size(max = 25, message = "{Size.AppParameter.value.validation}")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Size(max = 250, message = "{Size.AppParameter.description.validation}")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(LocalDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
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
        hash = 13 * hash + Objects.hashCode(this.symbol);
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + Objects.hashCode(this.value);
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
        final AppParameter other = (AppParameter) obj;
        if (!Objects.equals(this.symbol, other.symbol)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }



    @Override
    public String toString() {
        return "AppParameter{" 
                + " parameterId=" + parameterId 
                + ", symbol=" + symbol 
                + ", name=" + name 
                + ", value=" + value 
                + ", description=" + description
                + ", lastModificationDate=" + lastModificationDate 
                + ", createDate=" + createDate
                + '}';
    }

}
