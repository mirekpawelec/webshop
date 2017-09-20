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
public class FilterProduct extends Product{
    private BigDecimal minUnitPrice;
    private BigDecimal maxUnitPrice;

    public FilterProduct() {
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
        return "FilterProducts{" + "minUnitPrice=" + minUnitPrice + ", maxUnitPrice=" + maxUnitPrice + '}';
    }

}
