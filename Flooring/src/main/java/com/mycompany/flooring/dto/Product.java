/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooring.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author cas
 */
public class Product {


    private String productType;

    private BigDecimal costPsf;

    private BigDecimal laborCostPsf;


    /**
     * @return the productType
     */
    public String getProductType() {
        return productType;
    }

    /**
     * @param productType the productType to set
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

  
    /**
     * @return the costPsf
     */
    public BigDecimal getCostPsf() {
        return costPsf;
    }

    /**
     * @param costPsf the costPsf to set
     */
    public void setCostPsf(BigDecimal costPsf) {
        this.costPsf = costPsf;
    }

    /**
     * @return the laborCostPsf
     */
    public BigDecimal getLaborCostPsf() {
        return laborCostPsf;
    }

    /**
     * @param laborCostPsf the laborCostPsf to set
     */
    public void setLaborCostPsf(BigDecimal laborCostPsf) {
        this.laborCostPsf = laborCostPsf;
    }

}
