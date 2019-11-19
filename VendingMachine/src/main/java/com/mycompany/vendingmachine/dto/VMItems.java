/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author cas
 */
public class VMItems {

    private BigDecimal price;
    private int id;
    private String name;
    private int quanity;

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.price);
        hash = 13 * hash + this.id;
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + this.quanity;
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
        final VMItems other = (VMItems) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.quanity != other.quanity) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        return true;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the quanity
     */
    public int getQuanity() {
        return quanity;
    }

    /**
     * @param quanity the quanity to set
     */
    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }

    public VMItems add(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
