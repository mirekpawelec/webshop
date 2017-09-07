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
public class Storageplace {
    private Long placeId;
    private String placeNo;
    private String placaName;
    private Long areaId;    
    private Integer height;
    private Integer width;
    private Integer depth;
    private Double volume;
    private String status;
    private LocalDateTime createDate;

    public Storageplace() {
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

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
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
        return "Storageplace{" + "placeId=" + placeId + ", placeNo=" + placeNo + ", placaName=" + placaName + ", areaId=" + areaId + ", height=" + height + ", width=" + width + ", depth=" + depth + ", volume=" + volume + ", status=" + status + ", createDate=" + createDate + '}';
    }
    
}
