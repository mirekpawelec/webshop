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
public enum QualityStatus {
    
    _0(0, "Gat. I"), _2(2, "Gat. II"), _3(3, "Partia wadliwa"), _4(4, "Blokada techniczna"), _5(5, "Uszkodzone");
    
    private int numer;
    private String description;

    private QualityStatus(int numer, String description) {
        this.numer = numer;
        this.description = description;
    }

    public int getNumer() {
        return numer;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "QualityStatus{" + "numer=" + numer + ", description=" + description + '}';
    }
    
}
