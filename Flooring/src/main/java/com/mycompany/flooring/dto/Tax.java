/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooring.dto;

import java.math.BigDecimal;

/**
 *
 * @author cas
 */
public class Tax {

    //private String customerName;

    private String state;

    private BigDecimal taxRate;

//    /**
//     * @return the customerName
//     */
//    public String getCustomerName() {
//        return customerName;
//    }
//
//    /**
//     * @param customerName the customerName to set
//     */
//    public void setCustomerName(String customerName) {
//        this.customerName = customerName;
//    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the taxRate
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * @param taxRate the taxRate to set
     */
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
   
}
