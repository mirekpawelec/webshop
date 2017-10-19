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
@Table(name = "system_classes", uniqueConstraints = 
        @UniqueConstraint(columnNames= {"symbol", "name"}))
public class SystemClass {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id", nullable = false)
    private Long classId;
    
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

    
    
    public SystemClass() {
        this.lastModificationDate = LocalDateTime.now();
        this.createDate = LocalDateTime.now();
    }
    
    
    
    public boolean isNew(){
        return this.getClassId()==null;
    }
    
    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    @NotEmpty(message = "{NotEmpty.SystemClass.symbol.validation}")
    @Size(max = 50, message = "{Size.SystemClass.symbol.validation}")
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @NotEmpty(message = "{NotEmpty.SystemClass.name.validation}")
    @Size(max = 50, message = "{Size.SystemClass.name.validation}")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty(message = "{NotEmpty.SystemClass.value.validation}")
    @Size(max = 25, message = "{Size.SystemClass.value.validation}")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Size(max = 250, message = "{Size.SystemClass.description.validation}")
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
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.value);
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
        final SystemClass other = (SystemClass) obj;
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
        return "SystemClass{" + "classId=" + classId 
                              + ", symbol=" + symbol 
                              + ", name=" + name 
                              + ", value=" + value 
                              + ", description=" + description
                              + ", lastModificationDate=" + lastModificationDate 
                              + ", createDate=" + createDate + '}';
    }

}
