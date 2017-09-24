/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model;

import java.math.BigDecimal;

/**
 *
 * @author mirek
 */
public class ProductFilter extends Product{
    private boolean inStock;
    private BigDecimal minUnitPrice;
    private BigDecimal maxUnitPrice;

    public ProductFilter() {
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public BigDecimal getMinUnitPrice() {
        return minUnitPrice;
    }

    public void setMinUnitPrice(BigDecimal minUnitPrice) {
        this.minUnitPrice = minUnitPrice;
    }

    public BigDecimal getMaxUnitPrice() {
        return maxUnitPrice;
    }

    public void setMaxUnitPrice(BigDecimal maxUnitPrice) {
        this.maxUnitPrice = maxUnitPrice;
    }

    @Override
    public String toString() {
        return "ProductFilter{" + "inStock=" + inStock + ", minUnitPrice=" + minUnitPrice + ", maxUnitPrice=" + maxUnitPrice + '}';
    }
}
