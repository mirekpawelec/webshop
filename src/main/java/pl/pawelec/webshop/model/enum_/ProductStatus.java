/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.enum_;
/**
 *
 * @author mirek
 */
public enum ProductStatus {
    
    OK("OK", "OK"),
    BL("BL", "Blocked"),
    FI("FI", "Canceled");
    
    private String productStatusType;
    private String productStatusDescription;

    private ProductStatus(String productStatusType, String productStatusDescription) {
        this.productStatusType = productStatusType;
        this.productStatusDescription = productStatusDescription;
    }

    public String getProductStatusType() {
        return productStatusType;
    }

    public String getProductStatusDescription() {
        return productStatusDescription;
    }
    
    @Override
    public String toString() {
        return "ProductStatus{" + "productStatusType=" + productStatusType + ", productStatusDescription=" + productStatusDescription + '}';
    }
    
}
