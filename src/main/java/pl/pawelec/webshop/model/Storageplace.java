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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import pl.pawelec.webshop.converter.TimestampToLocalDateTimeConverter;

/**
 *
 * @author mirek
 */
@Entity
@Table(name = "storageplace")
public class Storageplace implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "place_id", nullable = false)
    private Long placeId;
    
    @Column(name = "place_no", nullable = false, unique = true, length = 25)
    private String placeNo;
    
    @Column(name = "name", nullable = false, length = 100)
    private String placaName;
    
    @JoinColumn(name = "area_id", referencedColumnName = "area_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Storagearea areaId;    
    
    @Column(nullable = false, length = 25)
    private String type;
    
    @Column(precision = 4)
    private Integer height;
    
    @Column(precision = 4)
    private Integer width;
    
    @Column(precision = 4)
    private Integer depth;
    
    @Column(precision = 4, scale = 2)
    private Double volume;
    
    @Column(length = 2)
    private String status;
    
    @Column(name = "c_date")
    @Convert(converter = TimestampToLocalDateTimeConverter.class)
    private LocalDateTime createDate;

    
    
    @OneToMany(mappedBy = "place", fetch = FetchType.EAGER)
    private Set<Repository> repositorySet = new HashSet<Repository>();
    
    @OneToMany(mappedBy = "place", fetch = FetchType.EAGER)
    private Set<Delivery> deliverySet = new HashSet<Delivery>();;
    
    
    
    public Storageplace() {
        areaId = new Storagearea();
    }

    public Storageplace(Long placeId) {
        this.placeId = placeId;
    }

    public Storageplace(Long placeId, String placeNo) {
        this.placeId = placeId;
        this.placeNo = placeNo;
    }
    
    
    
    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public String getPlaceNo() {
        return placeNo;
    }

    public void setPlaceNo(String placeNo) {
        this.placeNo = placeNo;
    }

    public String getPlacaName() {
        return placaName;
    }

    public void setPlacaName(String placaName) {
        this.placaName = placaName;
    }

    public Storagearea getAreaId() {
        return areaId;
    }

    public void setAreaId(Storagearea areaId) {
        this.areaId = areaId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
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

    public Set<Repository> getRepositorySet() {
        return repositorySet;
    }

    public void setRepositorySet(Set<Repository> repositorySet) {
        this.repositorySet = repositorySet;
    }

    public Set<Delivery> getDeliverySet() {
        return deliverySet;
    }

    public void setDeliverySet(Set<Delivery> deliverySet) {
        this.deliverySet = deliverySet;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.placeId);
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
        final Storageplace other = (Storageplace) obj;
        if (!Objects.equals(this.placeId, other.placeId)) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {        
        return "Storageplace{" + "placeId=" + placeId + ", placeNo=" + placeNo + ", placaName=" + placaName + ", areaId=" + areaId 
             + ", type=" + type + ", height=" + height + ", width=" + width + ", depth=" + depth + ", volume=" + volume 
             + ", status=" + status + ", createDate=" + createDate + ", repositorySet=" + repositorySet.size() + ", deliverySet=" + deliverySet.size() + '}';
    }
}
