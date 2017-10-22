/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import pl.pawelec.webshop.converter.TimestampToLocalDateTimeConverter;

/**
 *
 * @author mirek
 */
@Entity
@Table(name = "storagearea")
public class Storagearea implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id", nullable = false)
    private Long areaId;
    
    @Column(nullable = false, unique = true, length = 100)
    private String name;
   
    @Column(length = 255)
    private String description;

    @Column(name = "c_date")
    @Convert(converter = TimestampToLocalDateTimeConverter.class)
    private LocalDateTime createDate;
    
    
    
    @OneToMany(mappedBy = "areaId", fetch = FetchType.EAGER)
    private Set<Storageplace> storageplaceSet = new HashSet<Storageplace>();
    
    
    
    public Storagearea() {
    }

    
    
    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Set<Storageplace> getStorageplaceSet() {
        return storageplaceSet;
    }

    public void setStorageplaceSet(Set<Storageplace> storageplaceSet) {
        this.storageplaceSet = storageplaceSet;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.areaId);
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
        final Storagearea other = (Storagearea) obj;
        if (!Objects.equals(this.areaId, other.areaId)) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return "Storagearea{" 
                + "areaId=" + areaId 
                + ", name=" + name 
                + ", description=" + description 
                + ", storageplaceSet=" + storageplaceSet.size() 
                + '}';
    }
    
    
}
